import org.apache.log4j.DailyRollingFileAppender

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// GORM
grails.gorm.failOnError=true
grails.gorm.default.mapping = {
    version true
}

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.serverURL = "http://localhost:8080/school-enrollment"
    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://localhost:8080/school-enrollment"
    }
}

// log4j configuration

log4j = {
    //
    appenders {
        console name:'stdout', layout:pattern(conversionPattern: '%-5p %d{ISO8601} [%c{1}] [%t] [%X{userId}|%X{sessionId}] - %m%n')
        appender new DailyRollingFileAppender(
                name: 'school-enrollment',
                fileName: "shared/logs/school-enrollment.log".toString(),
                datePattern: "-yyyy-MM-dd",
                layout: pattern(conversionPattern: '%-5p %d{ISO8601} [%c{1}] [%t] [%X{userId}|%X{sessionId}] - %m%n')
        )
        appender new DailyRollingFileAppender(
                name: 'request',
                fileName: "shared/logs/school-enrollment-request.log".toString(),
                datePattern: ".yyyy-MM-dd",
                layout: pattern(conversionPattern: '%d{dd-MMM-yyyy HH:mm:ss,SSS}\t%X{userId}\t%X{sessionId}\t%X{requestMethod}\t%X{requestPath}\t%X{requestSize}\t%X{userAgent}\t%X{serverAddr}\t%X{remoteAddr}\t%t\t%m%n')
        )

        rollingFile name: "stacktrace", maxFileSize: 1024, file: "shared/logs/school-enrollment-stacktrace.log"
    }

    root {
        error 'stdout', 'school-enrollment'
        additivity: true
    }

    info  additivity: false, request: ["grails.app.filters.school.enrollment.RequestInfoFilters"]

    debug 'grails.app.services.school.enrollment',
            'grails.app.controllers.school.enrollment',
            'school-enrollment'

    error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
            'org.codehaus.groovy.grails.web.pages', //  GSP
            'org.codehaus.groovy.grails.web.sitemesh', //  layouts
            'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
            'org.codehaus.groovy.grails.web.mapping', // URL mapping
            'org.codehaus.groovy.grails.commons', // core / classloading
            'org.codehaus.groovy.grails.plugins', // plugins
            'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
            'org.springframework',
            'org.hibernate',
            'net.sf.ehcache.hibernate'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'school.enrollment.AppUser'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'school.enrollment.AppUserAppRole'
grails.plugins.springsecurity.authority.className = 'school.enrollment.AppRole'

grails.plugins.springsecurity.securityConfigType = "InterceptUrlMap"

grails.plugins.springsecurity.interceptUrlMap = [
        '/static/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/js/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/css/**': [ 'IS_AUTHENTICATED_ANONYMOUSLY'],
        '/images/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/login/**':    ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/logout/**':   ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/j_spring_security_check': ['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/admin/**':    ["hasAnyRole('ROLE_ADMIN')"],
        '/**':          ["hasAnyRole('ROLE_ADMIN', 'ROLE_USER')"],

]

// This is where your app will look for externalized configuration.
// Change if you have additional configuration files you want to use.

addConfigFileIfExists('shared/conf/Config.groovy')
addConfigFileIfExists('shared/conf/DataSource.groovy')

private addConfigFileIfExists(String fileLocation) {
    if (! grails.config.locations) {
        grails.config.locations = []
    }
    def file = new File(fileLocation)

    if (file.exists()) {
        println("[${appName}] Using config override file: ${file.canonicalPath}")
        grails.config.locations << "file:${file}"
    } else {
        println("[${appName}] warning: file ${file.canonicalPath} not found, using default settings.  To override those settings, place a file at that location.")
    }
}
