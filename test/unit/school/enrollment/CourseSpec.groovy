package school.enrollment

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*
import spock.lang.Specification
import grails.buildtestdata.mixin.Build
import grails.validation.ValidationException

@TestFor(Course)
@Build([Course])
class CourseSpec extends Specification {

    def "Course size validation"() {
        given:
            def course = Course.build()
            course.size = size
            course.maxSize = maxSize
        when:
            def result = course.validate()
        then:
            result == isValid
            course.errors.hasFieldErrors('size') == hasFieldErrors
        where:
            size | maxSize | isValid | hasFieldErrors
            5	 | 10      | true    | false
            10	 | 10      | true    | false
            15	 | 10      | false   | true

    }
}
