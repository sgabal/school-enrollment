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
            def courses = Course.list([offset:params.start, max:params.limit])
            def results = courses.each { course ->
                persistentProperties(domain:course)
            }
            render( [success: true, courses: results, total: courses.size()] as JSON )
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
