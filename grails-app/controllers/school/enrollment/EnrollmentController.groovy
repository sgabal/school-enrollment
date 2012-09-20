package school.enrollment


import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor
import static school.enrollment.ContextHolder.*

class EnrollmentController {

    def get() {
        render( [success:true, enrollments:[]] as JSON )
    }

    def save() {
        def student = Student.findByUserName(userId)
        def course = Course.findByNumber(params.number)
        student.addToEnrolled(course)
        student.save()
        render( [success:true, enrollments: [number:course.number, userName:userId]] as JSON )
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
