package school.enrollment

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor

class CourseController {

    def get() {
        if (params.long('id')) {
            def course = Course.get(params.id)
            def results = persistentProperties(domain:course)
            render( [success:true, courses: results] as JSON )
        } else {
            def courses = Course.findAllByTermAndSubject(params.term, params.subject, [offset:params.start, max:params.limit])
            def total = Course.countByTermAndSubject(params.term, params.subject)
            def results = courses.collect { course ->
                def result = persistentProperties(domain:course)
                result.prerequisite = course.prerequisite?.description
                return result
            }
            render( [success: true, courses: results, total: total] as JSON )
        }
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

    CourseController() {
        GroovyDynamicMethodsInterceptor interceptor = new GroovyDynamicMethodsInterceptor(this)
        interceptor.addDynamicMethodInvocation(new PersistentPropertiesMethod())
    }
}
