package school.enrollment


import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor
import static school.enrollment.ContextHolder.*
import grails.validation.ValidationException

class EnrollmentController {

    def enrollmentService

    def get =  {
        log.debug "action[get]::${params}"
        render( [success:true, enrollments:[]] as JSON )
    }

    def save = { EnrollmentRequest request ->
        def student = Student.findByUserName(userId)
        if (!student) {
            request.errors.reject('student.profile.required')
            throw new ValidationException("Student profile required", request.errors)
        }
        enrollmentService.enroll(request.courseNumber)
        render( [success:true, enrollments: [number:request.courseNumber]] as JSON )
    }

    def delete = {
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

class EnrollmentRequest {
    String courseNumber

    String toString() {
        "${courseNumber}"
    }
}
