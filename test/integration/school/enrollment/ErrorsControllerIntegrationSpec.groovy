package school.enrollment

import static org.junit.Assert.*


import grails.converters.JSON
import grails.plugin.spock.IntegrationSpec;
import grails.test.mixin.*
import grails.test.mixin.support.*
import grails.validation.ValidationException

import org.hibernate.HibernateException
import org.junit.*
import org.springframework.http.HttpStatus


class ErrorsControllerIntegrationSpec extends IntegrationSpec {

    def controller
    def grailsApplication

    def setup() {
        controller = new ErrorsController()
    }

    def "Validation Exception with field errors"() {
        setup:
            def student = new Student()
            student.validate()

            controller.request.exception = new RuntimeException(new ValidationException("", student.errors))
        when:
            controller.handle()
        then:
            controller.response.status == HttpStatus.OK.value

            def response = JSON.parse(controller.response.contentAsString)
            response.success == false
            response.errors.size() == 2
            response.errors[0].field =='firstName'
            response.errors[0].message =='Property [firstName] of class [class school.enrollment.Student] cannot be null'
    }

    def "Validation Exception with object errors"() {
        setup:
            def request = new EnrollmentRequest()
            request.errors.reject('student.profile.required')

            controller.request.exception = new RuntimeException(new ValidationException("", request.errors))
        when:
            controller.handle()
        then:
            controller.response.status == HttpStatus.OK.value

            def response = JSON.parse(controller.response.contentAsString)
            response.success == false
            response.errors.size() == 1
            response.errors[0].object == 'school.enrollment.EnrollmentRequest'
            response.errors[0].message == 'You must create a profile before enrolling in classes'
    }

    def "Unexpected Exception with showInternalErrors = true"() {
        setup:
            grailsApplication.config.showInternalErrors = true

            controller.request.exception = new RuntimeException(new HibernateException("transient collection error"))
        when:
            controller.handle()
        then:
            def response = JSON.parse(controller.response.contentAsString)
            response.success == false
            response.errors.size() == 1
            response.errors[0].message == 'org.hibernate.HibernateException: transient collection error'
    }

    def "Unexpected Exception with showInternalErrors = false"() {
        setup:
            ContextHolder.setSessionId("3A7C2BEFB999B1778DD45CCFD03269E3")
            grailsApplication.config.showInternalErrors = false
            controller.request.exception = new RuntimeException(new HibernateException("transient collection error"))
        when:
            controller.handle()
        then:
            def response = JSON.parse(controller.response.contentAsString)
            response.success == false
            response.errors.size() == 1
            response.errors[0].message == "Internal Error [Session Id='3A7C2BEFB999B1778DD45CCFD03269E3']"
    }

}

