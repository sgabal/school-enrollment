package school.enrollment

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor
import static school.enrollment.ContextHolder.*

class MyCourseController {

    def get() {
        def student = Student.findByUserName(userId)
        def results = []

        if (student) {
            results = student.enrolled.each { course ->
                persistentProperties(domain:course)
            }
        }

        render( [success: true, mycourses: results, total: results.size()] as JSON )
    }

    def save() {
        render( [success: true] as JSON )
    }

    def delete() {
        render( [success: true] as JSON )
    }

    def beforeInterceptor = {
        log.debug "action[${actionUri}]::${params}"
    }

    MyCourseController() {
        GroovyDynamicMethodsInterceptor interceptor = new GroovyDynamicMethodsInterceptor(this)
        interceptor.addDynamicMethodInvocation(new PersistentPropertiesMethod())
    }
}
