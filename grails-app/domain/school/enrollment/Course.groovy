package school.enrollment

import static school.enrollment.ContextHolder.userId
import static school.enrollment.ContextHolder.userId

class Course {

    String term
    String department
    String subject
    String number
    String name
    String startTime
    String endTime
    String days
    Integer credits
    String prerequisite
    String location
    String instructor
    String status
    Integer size
    Integer maxSize

    Date    dateCreated
    Date    lastUpdated
    String  createdBy
    String  updatedBy

    static belongsTo = Student
    static hasMany = [students:Student]

    static constraints = {
        term(nullable:true)
        department(nullable:true)
        subject(nullable:true)
        number(nullable:true, unique:true)
        name(nullable:true)
        startTime(nullable:true)
        endTime(nullable:true)
        days(nullable:true)
        credits(nullable:true)
        prerequisite(nullable:true)
        location(nullable:true)
        instructor(nullable:true)
        status(nullable:true)
        size(nullable:true, validator: { size, course -> size <= course.maxSize })
        maxSize(nullable:true)
        students(nullable: true)

        createdBy(nullable: true)
        updatedBy(nullable: true)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
    }

    def beforeInsert = {
        createdBy = userId
        updatedBy = createdBy
    }

    def beforeUpdate = {
        updatedBy = userId
    }
}
