package school.enrollment


import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor

class EnrollmentController {

    def get() {
        render( [success:true, enrollments:[]] as JSON )
    }

    def save() {
        render( [success:true] as JSON )
    }

    def delete() {
        render( [success:true] as JSON )
    }

    def beforeInterceptor = {
        log.debug "action[${actionUri}]::${params}"
    }

    EnrollmentController() {
        GroovyDynamicMethodsInterceptor interceptor = new GroovyDynamicMethodsInterceptor(this)
        interceptor.addDynamicMethodInvocation(new PersistentPropertiesMethod())
    }
}
