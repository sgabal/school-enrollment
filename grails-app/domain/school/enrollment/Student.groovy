package school.enrollment

import static school.enrollment.ContextHolder.*

class Student {

    String firstName
    String lastName
    String middleName
    Date birthDate
    String gender
    Integer grade
    String email
    String userName

    Date    dateCreated
    Date    lastUpdated
    String  createdBy
    String  updatedBy

    static hasMany = [completed:Course, enrolled:Course]

    static constraints = {
        birthDate(nullable:true)
        email(nullable:true)
        firstName(nullable:false, blank:false)
        gender(nullable:true)
        grade(nullable:true)
        lastName(nullable:false, blank:false)
        middleName(nullable:true)
        userName(nullable: true)
        completed(nullable: true)
        enrolled(nullable: true)

        createdBy(nullable: true)
        updatedBy(nullable: true)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
    }

    static mapping = {
        completed joinTable: [name: "completed_courses", key: 'student_id']
        enrolled joinTable: [name: "enrolled_courses", key: 'student_id']
    }

    def beforeInsert = {
        createdBy = userId
        updatedBy = createdBy
    }

    def beforeUpdate = {
        updatedBy = userId
    }

}
