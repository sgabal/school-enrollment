package school.enrollment

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Course)
@Build([Course])
class CourseSpec extends Specification {

    def "Course size validation"() {
        given:
            def course = Course.build()
            course.seats = seats
            course.maxSize = maxSize
        when:
            def result = course.validate()
        then:
            result == isValid
            course.errors.hasFieldErrors('seats') == hasFieldErrors
        where:
            seats | maxSize | isValid | hasFieldErrors
            5	 | 10      | true    | false
            10	 | 10      | true    | false
            15	 | 10      | false   | true

    }
}
