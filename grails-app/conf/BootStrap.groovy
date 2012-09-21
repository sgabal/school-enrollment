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
                term: 'Fall 2012', department: 'MATH', identifier: '5001', name: 'Algebra I',
                startTime: '09:00 A.M', endTime: '10:00 A.M',  days: 'T,TH', credits: 4,
                prerequisite: null, location: 'TCEASTBANK ', instructor: 'Roberts, Bob',
                status: 'OPEN', seats: 10, maxSize: 15, subject:'Mathematics'
        ).save(flush: true)

        def algebra2 = new Course(
                term: 'Fall 2012', department: 'MATH', identifier: '5002', name: 'Algebra II',
                startTime: '09:00 A.M', endTime: '10:00 A.M',  days: 'M,W', credits: 4,
                prerequisite: algebra1, location: 'TCEASTBANK ', instructor: 'Roberts, Bob',
                status: 'OPEN', seats: 5, maxSize: 15, subject:'Mathematics'
        ).save(flush: true)

        def geometry = new Course(
                term: 'Fall 2012', department: 'MATH', identifier: '5010', name: 'Geometry',
                startTime: '02:00 P.M', endTime: '03:00 P.M',  days: 'M,W', credits: 4,
                prerequisite: algebra2, location: 'TCEASTBANK ', instructor: 'Hobster, Jennifer',
                status: 'OPEN', seats: 10, maxSize: 25, subject:'Mathematics'
        ).save(flush: true)

        def calculus1 = new Course(
                term: 'Fall 2012', department: 'MATH', identifier: '5020', name: 'Calculus I',
                startTime: '11:00 A.M', endTime: '12:00 P.M',  days: 'M,W,F', credits: 4,
                prerequisite: geometry, location: 'TCEASTBANK ', instructor: 'Tribeca, Tim',
                status: 'OPEN', seats: 5, maxSize: 15, subject:'Mathematics'
        ).save(flush: true)

        def calculus2 = new Course(
                term: 'Fall 2012', department: 'MATH', identifier: '5021', name: 'Calculus II',
                startTime: '03:00 P.M', endTime: '04:00 P.M',  days: 'M,W,F', credits: 4,
                prerequisite: calculus1, location: 'TCEASTBANK ', instructor: 'Tribeca, Tim',
                status: 'OPEN', seats: 10, maxSize: 30, subject:'Mathematics'
        ).save(flush: true)

        def statistics = new Course(
                term: 'Fall 2012', department: 'MATH', identifier: '5050', name: 'Statistics I',
                startTime: '09:00 A.M', endTime: '10:00 A.M',  days: 'M,W,F', credits: 5,
                prerequisite: null, location: 'TCWESTBANK ', instructor: 'Should, Josh',
                status: 'OPEN', seats: 10, maxSize: 15, subject:'Mathematics'
        ).save(flush: true)

        def digital = new Course(
                term: 'Fall 2012', department: 'CPRE', identifier: '9000', name: 'Intro to Digital Techniques',
                startTime: '011:00 A.M', endTime: '12:00 P.M',  days: 'M,W,F', credits: 5,
                prerequisite: null, location: 'TCWESTBANK ', instructor: 'Arnold, Tom',
                status: 'OPEN', seats: 10, maxSize: 15, subject:'Computer Engineering'
        ).save(flush: true)

        def compArch = new Course(
                term: 'Fall 2012', department: 'CPRE', identifier: '9010', name: 'Computer Architecture',
                startTime: '010:00 A.M', endTime: '12:00 P.M',  days: 'T,TH', credits: 5,
                prerequisite: null, location: 'TCWESTBANK ', instructor: 'Arnold, Tom',
                status: 'OPEN', seats: 15, maxSize: 15, subject:'Computer Engineering'
        ).save(flush: true)

        assert Course.count() == 8

    }
    def destroy = {
    }
}
