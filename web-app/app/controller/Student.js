Ext.define('SchoolEnrollment.controller.Student', {
    extend: 'Ext.app.Controller',

    refs: [

    ],

    views: [
        'student.StudentPanel'
    ],

    models: [
        'Student'
    ],

    stores: [
        'Student'
    ],

    init: function() {

        this.control({
        });

    }

});

