package school.enrollment

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.validation.ValidationException
import org.springframework.security.core.userdetails.User

@TestFor(EnrollmentController)
@Build([Student, Course])
class EnrollmentControllerSpec extends Specification {

    def "Student profile not created"() {
        setup:
            controller.enrollmentService = Mock(EnrollmentService)

            Course.build(identifier : '5000', department : 'MATH')

            def request = new EnrollmentRequest(courseNumber:'5000')

        when:
            controller.save(request)
        then:
            0 * controller.enrollmentService.enroll(_)
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'student.profile.required'
    }

    def "Successful enrollment"() {
        setup:
            controller.enrollmentService = Mock(EnrollmentService)

            ContextHolder.setUser(new User('testuser', 'passwd', true, false, true, false, []))

            Course.build(identifier : '5000', department : 'MATH')
            Student.build(userName : 'testuser')

            def request = new EnrollmentRequest(courseNumber:'5000')

        when:
            controller.save(request)
        then:
            1 * controller.enrollmentService.enroll(request.courseNumber)

    }

}
