package school.enrollment

import grails.plugin.spock.IntegrationSpec
import grails.validation.ValidationException

class EnrollmentControllerIntegrationSpec extends IntegrationSpec {

    def enrollmentController

    def setup() {
        enrollmentController = new EnrollmentController()
    }

    def "Student profile doesn't exist"() {
        setup:
            def request = new EnrollmentRequest(courseNumber: '5000')
        when:
            enrollmentController.save(request)
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'student.profile.required'
    }


}
