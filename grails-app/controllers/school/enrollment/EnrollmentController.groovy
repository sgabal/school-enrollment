package school.enrollment


import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor
import static school.enrollment.ContextHolder.*

class EnrollmentController {

    def get() {
        log.debug "action[get]::${params}"
        render( [success:true, enrollments:[]] as JSON )
    }

    def save() {
        def student = Student.findByUserName(userId)
        def course = Course.findByIdentifier(params.identifier)
        student.addToEnrolled(course)
        student.save()
        render( [success:true, enrollments: [number:course.identifier, userName:userId]] as JSON )
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
