package school.enrollment

import grails.plugin.spock.IntegrationSpec
import grails.validation.ValidationException
import org.springframework.security.core.userdetails.User

class EnrollmentControllerIntegrationSpec extends IntegrationSpec {

    def controller

    def setup() {
        controller = new EnrollmentController()
        ContextHolder.setUser(new User('testuser', 'passwd', true, false, true, false, []))
    }

    def "Student profile doesn't exist"() {
        setup:
            def request = new EnrollmentRequest(courseNumber: '5000')
        when:
            controller.save(request)
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'student.profile.required'
    }

    def "Student already enrolled"() {
        setup:
            def student = new Student(userName : 'testuser', firstName: 'Test', lastName: 'User')
            def course = Course.findByDepartmentAndIdentifier('MATH', '5001')
            student.addToEnrolled(course)
            student.save(flush: true)

        when:
            controller.save(new EnrollmentRequest(courseNumber: '5001'))
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'course.enrolled.already'

    }

    def "Student missing prerequisite"() {
        setup:
            def student = new Student(
                    userName : 'testuser',
                    firstName: 'Test',
                    lastName: 'User',
                    enrolled: [],
                    completed: [])

            student.save(flush: true)

        when:
            controller.save(new EnrollmentRequest(courseNumber: '5002'))
        then:
            ValidationException e = thrown()
            e.errors.globalError.code == 'course.prerequisite.missing'
    }

    def "Successful enrollment"() {
        setup:
            def student = new Student(
                    userName : 'testuser',
                    firstName: 'Test',
                    lastName: 'User',
                    enrolled: [],
                    completed: [])

            student.save(flush: true)

        when:
            controller.save(new EnrollmentRequest(courseNumber: '5001'))
        then:
            student.enrolled.asList()[0].department == 'MATH'
            student.enrolled.asList()[0].identifier == '5001'

    }


}
