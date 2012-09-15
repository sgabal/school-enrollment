package school.enrollment

import org.springframework.web.context.request.RequestContextHolder

class ContextFilters {

    def springSecurityService

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                String sessionId = RequestContextHolder.requestAttributes?.sessionId
                if (sessionId) {
                    ContextHolder.setSessionId(sessionId)
                }

                if (springSecurityService.isLoggedIn()) {
                    ContextHolder.setUser(springSecurityService.principal)
                }

            }
            after = { Map model ->

            }
            afterView = { Exception e ->
                ContextHolder.clear()
            }
        }
    }
}
