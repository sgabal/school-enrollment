package school.enrollment

import org.springframework.security.core.userdetails.User

/**
 * Created with IntelliJ IDEA.
 * User: SGabal
 * Date: 9/14/12
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
class Context {
    def sessionId
    def user = new User('anonymous', 'anonymous', true, true, true, true, [])
}
