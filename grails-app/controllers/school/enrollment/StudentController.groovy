package school.enrollment

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor
import org.codehaus.groovy.grails.web.json.JSONObject

import static school.enrollment.ContextHolder.getUserId

class StudentController {

    def get() {
        def student = Student.findByUserName(userId)
        if (student) {
            def results = persistentProperties(domain:student)
            render( [success:true, students: results] as JSON )
        } else {
            render( [success:false, students: []] as JSON )
        }
    }

    def save() {
        def student
        if (params.id != JSONObject.NULL) {
            student = Student.get(params.id)
            student.properties = params
        } else {
            student = new Student(params)
            student.userName = userId
        }

        student = student.save(flush: true)
        def results = persistentProperties(domain:student)
        render( [success:true, students: results] as JSON )
    }

    def delete() {
        render 'delete'
    }

    def beforeInterceptor = {
        log.debug "action[${actionUri}]::${params}"
    }

    StudentController() {
        GroovyDynamicMethodsInterceptor interceptor = new GroovyDynamicMethodsInterceptor(this)
        interceptor.addDynamicMethodInvocation(new PersistentPropertiesMethod())
    }
}
