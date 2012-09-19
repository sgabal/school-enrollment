class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')

        '/home' (view: '/index')
        '/profile' (view: '/profile')
        '/course' (view: '/course')

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
	}
}
