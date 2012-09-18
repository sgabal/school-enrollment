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

        "/students/$id?" (controller: 'student', parseRequest: true) {
            action = [
                    GET: 'get',
                    PUT: 'save',
                    POST: 'save',
                    DELETE: 'delete'
            ]
        }
	}
}
