modules = {
    application {
        resource url:'js/application.js'
    }
    extjs {
        resource url:'js/extjs-4.0.7/resources/css/ext-all.css', disposition:'head'
        resource url:'js/extjs-4.0.7/ext-all.js', disposition:'head'
    }
    extensible {
        dependsOn 'extjs'
        resource url:'js/extensible-1.5.1/resources/css/extensible-all.css', disposition:'head'
        resource url:'js/extensible-1.5.1/extensible-all.js', disposition:'head'
    }
    profile {
        dependsOn 'extjs'
        resource url:'profile-app.js', disposition:'head'
        resource url:'app-override.js', disposition:'head'
    }
    course {
        dependsOn 'extjs'
        resource url:'course-app.js', disposition:'head'
        resource url:'app-override.js', disposition:'head'
    }
    calendar {
        dependsOn 'extensible'
        resource url:'calendar-app.js', disposition:'head'
        resource url:'app-override.js', disposition:'head'
    }
    admin {
        dependsOn 'extjs'
    }
}