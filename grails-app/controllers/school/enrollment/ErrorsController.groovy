package school.enrollment

import org.springframework.http.HttpStatus

import grails.converters.*

class ErrorsController {

    def messageSource
    def grailsApplication

    def index = { }

    def handle = {

        def cause = request.exception?.getCause()

        def results = [success:false]

        if (cause instanceof grails.validation.ValidationException) {

            def fieldErrors = cause.errors.fieldErrors.collect { error ->
                return ['field':error.field , 'message' : messageSource.getMessage(error, Locale.getDefault())]
            }

            def objectErrors = cause.errors.globalErrors.collect { error ->
                return ['object':error.objectName , 'message' : messageSource.getMessage(error, Locale.getDefault())]
            }

            results.errors = objectErrors + fieldErrors
            response.status = HttpStatus.OK.value

        } else if (grailsApplication.config.showInternalErrors && request.exception) {
            results.errors = [[message:request.exception.message]]
        } else {
            def message = messageSource.getMessage('school.enrollment.internal.error', [ContextHolder.getSessionId()].toArray(), Locale.getDefault())
            results.errors = [[message:message]]
        }

        render results as JSON
    }


}

