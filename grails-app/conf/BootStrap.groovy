import school.enrollment.AppRole
import school.enrollment.AppUser
import school.enrollment.AppUserAppRole
import school.enrollment.Course

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

        def algebra1 = new Course(
                term: 'Fall 2012', department: 'MATH', number: '5001', name: 'Algebra I',
                startTime: '09:00 A.M', endTime: '10:00 A.M',  days: 'T,TH', credits: 4,
                prerequisite: 'NONE', location: 'TCEASTBANK ', instructor: 'Roberts, Bob',
                status: 'OPEN', size: 10, maxSize: 15,
                subject:'Mathematics'
        ).save(flush: true)

        def algebra2 = new Course(
                term: 'Fall 2012', department: 'MATH', number: '5002', name: 'Algebra II',
                startTime: '09:00 A.M', endTime: '10:00 A.M',  days: 'M,W', credits: 4,
                prerequisite: 'NONE', location: 'TCEASTBANK ', instructor: 'Roberts, Bob',
                status: 'OPEN', size: 15, maxSize: 15,
                subject:'Mathematics'
        ).save(flush: true)

        def geometry = new Course(
                term: 'Fall 2012', department: 'MATH', number: '5010', name: 'Geometry',
                startTime: '02:00 P.M', endTime: '03:00 P.M',  days: 'M,W', credits: 4,
                prerequisite: 'MATH 5002', location: 'TCEASTBANK ', instructor: 'Hobster, Jennifer',
                status: 'OPEN', size: 10, maxSize: 25,
                subject:'Mathematics'
        ).save(flush: true)

        def calculus1 = new Course(
                term: 'Fall 2012', department: 'MATH', number: '5020', name: 'Calculus I',
                startTime: '11:00 A.M', endTime: '12:00 P.M',  days: 'M,W,F', credits: 4,
                prerequisite: 'MATH 5010', location: 'TCEASTBANK ', instructor: 'Tribeca, Tim',
                status: 'OPEN', size: 5, maxSize: 15,
                subject:'Mathematics'
        ).save(flush: true)

        def calculus2 = new Course(
                term: 'Fall 2012', department: 'MATH', number: '5021', name: 'Calculus II',
                startTime: '03:00 P.M', endTime: '04:00 P.M',  days: 'M,W,F', credits: 4,
                prerequisite: 'MATH 5020', location: 'TCEASTBANK ', instructor: 'Tribeca, Tim',
                status: 'OPEN', size: 10, maxSize: 30,
                subject:'Mathematics'
        ).save(flush: true)

    }
    def destroy = {
    }
}
