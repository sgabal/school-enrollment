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

    Date    dateCreated
    Date    lastUpdated
    String  createdBy
    String  updatedBy

    static constraints = {
        birthDate(nullable:true, blank:true)
        email(nullable:true, blank:true)
        firstName(nullable:true, blank:true)
        gender(nullable:true, blank:true)
        grade(nullable:true, blank:true)
        lastName(nullable:false, blank:false)
        middleName(nullable:true, blank:true)

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
