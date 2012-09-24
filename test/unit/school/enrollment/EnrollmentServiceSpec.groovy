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
            def course = Course.build(department : 'MATH', identifier : '5000')
            Student.build(userName : 'testuser', enrolled:[course])
        when:
            service.enroll('5000')
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'course.enrolled.already'
    }

    def "Student missing prerequisite"() {
        setup:
            def course = Course.build(department : 'MATH', identifier : '5000')
            Course.build(department : 'MATH', identifier : '5001', prerequisite : course)
            Student.build(userName : 'testuser', enrolled : [], completed : [])
        when:
            service.enroll('5001')
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'course.prerequisite.missing'
    }

}
