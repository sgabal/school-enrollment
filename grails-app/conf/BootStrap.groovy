import school.enrollment.AppRole
import school.enrollment.AppUser
import school.enrollment.AppUserAppRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new AppRole(authority: 'ROLE_ADMIN').save(flush: true)
        def userRole = new AppRole(authority: 'ROLE_USER').save(flush: true)

        def adminUser = new AppUser(username: 'admin', enabled: true, password: 'admin')
        adminUser.save(flush: true)

        def sgabal = new AppUser(username: 'sgabal', enabled: true, password: 'sgabal')
        sgabal.save(flush: true)

        AppUserAppRole.create(adminUser, adminRole, true)
        AppUserAppRole.create(sgabal, userRole, true)

        assert AppUser.count() == 2
        assert AppRole.count() == 2
        assert AppUserAppRole.count() == 2
    }
    def destroy = {
    }
}
