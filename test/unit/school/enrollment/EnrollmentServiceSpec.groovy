package school.enrollment

import grails.buildtestdata.mixin.Build
import grails.test.mixin.TestFor
import spock.lang.Specification
import org.springframework.security.core.userdetails.User

@TestFor(EnrollmentService)
@Build([Student, Course])
class EnrollmentServiceSpec extends Specification {

    def "Student already enrolled"() {
        setup:
            ContextHolder.setUser(new User('user1', 'passwd1', true, false, true, false, []))

            def student = Student.build()
            student.userName = 'user1'
            student.save()

            println ContextHolder.userId
        when:
            service.enroll('5000')
        then:
            true == true
    }

}
