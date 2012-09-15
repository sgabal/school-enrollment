import school.enrollment.AppRole
import school.enrollment.AppUser
import school.enrollment.AppUserAppRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new AppRole(authority: 'ROLE_ADMIN').save(flush: true)

        def adminUser = new AppUser(username: 'admin', enabled: true, password: 'admin')
        adminUser.save(flush: true)

        AppUserAppRole.create(adminUser, adminRole, true)

        assert AppUser.count() == 1
        assert AppRole.count() == 1
        assert AppUserAppRole.count() == 1
    }
    def destroy = {
    }
}
