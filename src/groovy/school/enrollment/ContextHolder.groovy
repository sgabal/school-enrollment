package school.enrollment

import org.springframework.security.core.userdetails.User

/**
 * Created with IntelliJ IDEA.
 * User: SGabal
 * Date: 9/14/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
class ContextHolder {
    private static ThreadLocal<Context> threadLocal = new ThreadLocal<Context>();

    static void setUser(User user) {
        getContext().setUser(user)
    }

    static User getUser() {
        return getContext().getUser()
    }

    static String getUserId() {
        return getContext().getUser().getUsername()
    }

    static String getSessionId() {
        return getContext().getSessionId()
    }

    static void setSessionId(String sessionId) {
        getContext().setSessionId(sessionId)
    }

    static getContext() {
        def context = threadLocal.get()
        if (!context) {
            context = new Context()
            threadLocal.set(context)
        }
        return context
    }

    static void clear() {
        threadLocal.remove()
    }

}
