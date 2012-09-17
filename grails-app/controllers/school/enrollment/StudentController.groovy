package school.enrollment

import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

class StudentController {

    def get() {
        render([success:true] as JSON)
    }

    def save() {
        log.debug params.id.class

        def student
        if (params.id != JSONObject.NULL) {
            log.debug 'query'
            student = Student.get(params.id)
        } else {
            log.debug 'create'
            student = new Student(params)
        }

        student.save(flush: true)
        render([success:true, data: student.properties] as JSON)
    }

    def delete() {
        render 'delete'
    }

    def beforeInterceptor = {
        log.debug "action[${actionUri}]::${params}"
    }
}
