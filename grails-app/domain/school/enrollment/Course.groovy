package school.enrollment

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
    }
}
