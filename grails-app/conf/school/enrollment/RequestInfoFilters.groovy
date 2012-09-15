package school.enrollment

import org.apache.commons.lang.time.StopWatch


class RequestInfoFilters {

    def filters = {
        all(controller: '*', action: '*') {
            def stopWatch = new StopWatch()
            before = {
                if (log.isInfoEnabled()) {
                    stopWatch.reset()
                    stopWatch.start()
                }
            }
            after = { Map model ->
            }
            afterView = { Exception e ->
                if (log.isInfoEnabled() && stopWatch != null) {
                    stopWatch.stop()
                    log.info stopWatch.toString()
                }
            }
        }
    }
}
