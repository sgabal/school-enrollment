class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
        '/student.gsp' (view: '/student')
		"500"(view:'/error')
	}
}
