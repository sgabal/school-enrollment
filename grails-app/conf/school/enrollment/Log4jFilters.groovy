package school.enrollment

import org.apache.log4j.MDC
import org.springframework.web.context.request.RequestContextHolder
import org.apache.commons.lang.StringUtils

class Log4jFilters {

    static final String MDC_ATTRIBUTE_SESSION_ID = 'sessionId'
    static final String MDC_ATTRIBUTE_USER_ID = 'userId'
    static final String MDC_REQUEST_PATH = "requestPath"
    static final String MDC_REQUEST_METHOD = "requestMethod"
    static final String MDC_USER_AGENT = "userAgent"
    static final String MDC_REQUEST_SIZE = "requestSize"
    static final String MDC_SERVER_ADDR = "serverAddr"
    static final String MDC_REMOTE_ADDR = "remoteAddr"


    def dependsOn = [school.enrollment.ContextFilters]

    def filters = {
        all(controller: '*', action: '*') {
            before = {
                MDC.put(MDC_ATTRIBUTE_SESSION_ID, ContextHolder.sessionId)
                MDC.put(MDC_ATTRIBUTE_USER_ID, ContextHolder.userId)
                MDC.put(MDC_REQUEST_PATH, StringUtils.defaultString(request.getContextPath()) + StringUtils.defaultString(request.getServletPath()))
                MDC.put(MDC_REQUEST_METHOD, StringUtils.defaultString(request.getMethod()))
                MDC.put(MDC_USER_AGENT, StringUtils.defaultString(request.getHeader("User-Agent")))
                MDC.put(MDC_REQUEST_SIZE, String.valueOf(request.getContentLength()))
                MDC.put(MDC_SERVER_ADDR, StringUtils.defaultString(request.getLocalAddr()))
                MDC.put(MDC_REMOTE_ADDR, StringUtils.defaultString(request.getRemoteAddr()))

            }
            after = { Map model ->

            }
            afterView = { Exception e ->
                MDC.remove(MDC_ATTRIBUTE_SESSION_ID)
                MDC.remove(MDC_ATTRIBUTE_USER_ID)
                MDC.remove(MDC_REQUEST_PATH)
                MDC.remove(MDC_REQUEST_METHOD)
                MDC.remove(MDC_USER_AGENT)
                MDC.remove(MDC_REQUEST_SIZE)
                MDC.remove(MDC_SERVER_ADDR)
                MDC.remove(MDC_REMOTE_ADDR)
            }
        }
    }
}
