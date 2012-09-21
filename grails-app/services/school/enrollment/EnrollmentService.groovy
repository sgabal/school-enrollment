package school.enrollment

import static school.enrollment.ContextHolder.*
import grails.validation.ValidationException

class EnrollmentService {

    static transactional = true

    def grailsApplication

    def enroll(def courseNumber) {
        log.debug "Enrolling ${userId} in ${courseNumber}"

        def student = Student.findByUserName(userId)
        def course = Course.findByIdentifier(courseNumber)

        if (student.enrolled.contains(course)) {
            course.errors.reject('course.enrolled.already', [course.description] as String[], '')
            throw new ValidationException("Already enrolled", course.errors)
        }

        if (course.prerequisite && !student.completed.contains(course.prerequisite)) {
            course.errors.reject('course.prerequisite.missing', [course.prerequisite.description] as String[], '')
            throw new ValidationException("Prerequisite not met", course.errors)
        }

        student.addToEnrolled(course)

        course.seats = course.seats + 1

        return student.save()
    }
}
