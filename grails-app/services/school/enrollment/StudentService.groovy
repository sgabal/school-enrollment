package school.enrollment

import static school.enrollment.ContextHolder.*

class StudentService {

    def save(StudentRequest request) {

        def student
        if (request.id) {
            student = Student.get(request.id)
            student.properties = request.properties
        } else {
            student = new Student(request.properties)
            student.userName = userId
        }

        return student.save(flush: true)
    }}
