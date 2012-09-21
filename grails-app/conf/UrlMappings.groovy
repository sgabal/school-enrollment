class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")

        "500"(controller: 'errors', action:'handle')

        '/home' (view: '/index')
        '/profile' (view: '/profile')
        '/course' (view: '/course')
        '/admin' (view: '/admin')

        "/students/$id?" (controller: 'student', parseRequest: true) {
            action = [
                    GET: 'get',
                    PUT: 'save',
                    POST: 'save',
                    DELETE: 'delete'
            ]
        }

        "/courses/$id?" (controller: 'course', parseRequest: true) {
            action = [
                    GET: 'get',
                    PUT: 'save',
                    POST: 'save',
                    DELETE: 'delete'
            ]
        }

        "/mycourses/$id?" (controller: 'myCourse', parseRequest: true) {
            action = [
                    GET: 'get',
                    PUT: 'save',
                    POST: 'save',
                    DELETE: 'delete'
            ]
        }

        "/enrollments/$id?" (controller: 'enrollment', parseRequest: true) {
            action = [
                    GET: 'get',
                    PUT: 'save',
                    POST: 'save',
                    DELETE: 'delete'
            ]
        }


	}
}
