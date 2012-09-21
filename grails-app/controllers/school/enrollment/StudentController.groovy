package school.enrollment

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor
import org.codehaus.groovy.grails.web.json.JSONObject

import static school.enrollment.ContextHolder.getUserId

class StudentController {

    def studentService

    def get() {
        def student = Student.findByUserName(userId)
        if (student) {
            def results = persistentProperties(domain:student)
            render( [success:true, students: results] as JSON )
        } else {
            render( [success:false, students: []] as JSON )
        }
    }

    def save(StudentRequest request) {
        def student = studentService.save(request)
        def results = persistentProperties(domain:student)
        render( [success:true, students: results] as JSON )
    }

    def delete() {
        render( [success:true] as JSON )
    }

    def beforeInterceptor = {
        log.debug "action[${actionUri}]::${params}"
    }

    StudentController() {
        GroovyDynamicMethodsInterceptor interceptor = new GroovyDynamicMethodsInterceptor(this)
        interceptor.addDynamicMethodInvocation(new PersistentPropertiesMethod())
    }
}

class StudentRequest {
    Integer id
    String firstName
    String lastName
    String middleName
    String gender
    String email

    static constraints = {
        firstName(nullable: false)
        lastName(nullable: false)
        gender(nullable: false)
        email(nullable: false)
    }

    String toString() {
        "${firstName} ${lastName}"
    }
}