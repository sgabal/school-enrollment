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

//    def "Validation Exception with field errors"() {
//        setup:
//        def student = new Student()
//
//        student.errors.rejectValue('grade', 'priceProgram.notValidForVisualMerch', [student.visualMerchId] as String[], "")
//
//        controller.request.exception = new RuntimeException(new ValidationException("", student.errors))
//        when:
//        controller.handle()
//        then:
//        controller.response.status == HttpStatus.OK.value
//
//        def response = JSON.parse(controller.response.contentAsString)
//        response.success == false
//        response.errors.size() == 1
//        response.errors[0].field =='priceProgramId'
//        response.errors[0].message =='Price Program is not valid for Y2CXM4'
//    }
//
//    def "Validation Exception with object errors"() {
//        setup:
//        def request = new VisualMerchRequest()
//        request.visualMerchId = 'Y2CXM4'
//        request.priceProgramId = 'XPPCLDX121A001'
//        request.sellingMethod = 'PrePay'
//
//        request.errors.reject('priceProgram.notFound')
//
//        controller.request.exception = new RuntimeException(new ValidationException("", request.errors))
//        when:
//        controller.handle()
//        then:
//        controller.response.status == HttpStatus.OK.value
//
//        def response = JSON.parse(controller.response.contentAsString)
//        response.success == false
//        response.errors.size() == 1
//        response.errors[0].object =='com.lifetouch.fow.VisualMerchRequest'
//        response.errors[0].message =='Price Program not found'
//    }
//
//    def "Unexpected Exception with showInternalErrors = true"() {
//        setup:
//        grailsApplication.config.showInternalErrors = true
//
//        controller.request.exception = new RuntimeException(new HibernateException("transient collection error"))
//        when:
//        controller.handle()
//        then:
//        def response = JSON.parse(controller.response.contentAsString)
//        response.success == false
//        response.errors.size() == 1
//        response.errors[0].message == 'org.hibernate.HibernateException: transient collection error'
//    }
//
//    def "Unexpected Exception with showInternalErrors = false"() {
//        setup:
//        grailsApplication.config.showInternalErrors = false
//
//        controller.request.exception = new RuntimeException(new HibernateException("transient collection error"))
//        when:
//        controller.handle()
//        then:
//        def response = JSON.parse(controller.response.contentAsString)
//        response.success == false
//        response.errors.size() == 1
//        response.errors[0].message == "Internal Error [Session Id='UNITTEST']"
//    }

}

