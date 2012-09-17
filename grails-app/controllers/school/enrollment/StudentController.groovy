package school.enrollment

import grails.converters.JSON

class StudentController {

    def get() {
        render([success:true] as JSON)
    }

    def save() {
        render([success:true] as JSON)
    }

    def delete() {
        render 'delete'
    }

    def beforeInterceptor = {
        log.debug "action[${actionUri}]::${params}"
    }
}
