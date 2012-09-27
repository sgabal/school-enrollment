package school.enrollment

import static groovyx.gpars.GParsPool.withPool
import spock.lang.Specification

class GParsSpec extends Specification {

    def "Map Reduce"() {
        when:
            def result =  withPool {
                [0, 1, 2, 3, 4].parallel
                    .map { it + 1 }
                    .map { it ** 2 }
                    .reduce { a, b -> a + b }
            }
        then:
            result == 55
    }

    def "Fork Join"() {
        when:
            def squares = withPool {
                def numbers = [1, 2, 3, 4, 5, 6].makeConcurrent()
                numbers.collect{ it * it }.grep{ it < 10 }
            }
        then:
            squares == [1, 4, 9]
    }

}
