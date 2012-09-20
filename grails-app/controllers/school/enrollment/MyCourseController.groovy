package school.enrollment

import grails.converters.JSON
import org.codehaus.groovy.grails.commons.metaclass.GroovyDynamicMethodsInterceptor
import static school.enrollment.ContextHolder.*

class MyCourseController {

    def get() {
        def courses = Course.createCriteria().list(offset:params.start, max:params.limit) {
            and {
                students {
                    eq('userName', userId)
                }
            }
        }
        def results = courses.collect { course ->
            persistentProperties(domain:course)
        }
        render( [success: true, courses: results, total: Student.enrolled.size()] as JSON )
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
