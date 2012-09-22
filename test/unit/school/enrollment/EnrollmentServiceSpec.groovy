package school.enrollment

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification
import org.springframework.security.core.userdetails.User
import grails.validation.ValidationException

@TestFor(EnrollmentService)
@Build([Student, Course])
class EnrollmentServiceSpec extends Specification {

    def setup() {
        ContextHolder.setUser(new User('testuser', 'passwd', true, false, true, false, []))
    }

    def "Student already enrolled"() {
        setup:

            def course = Course.build()
            course.department = 'MATH'
            course.identifier = '5000'
            course.save()

            def student = Student.build()
            student.userName = 'testuser'
            student.addToEnrolled(course)
            student.save()

        when:
            service.enroll('5000')
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'course.enrolled.already'
    }

    def "Student missing prerequisite"() {
        setup:

            def algebral1 = Course.build()
            algebral1.department = 'MATH'
            algebral1.identifier = '5000'
            algebral1.save()

            def algebra2 = Course.build()
            algebra2.department = 'MATH'
            algebra2.identifier = '5001'
            algebra2.prerequisite = algebral1
            algebra2.save()

            def student = Student.build()
            student.userName = 'testuser'
            student.enrolled = []
            student.completed = []

            student.save()

        when:
            service.enroll('5001')
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'course.prerequisite.missing'
    }

}
