Ext.define('Course.controller.Course', {
    extend: 'Ext.app.Controller',

    refs: [
    ],

    views: [
        'course.CourseForm',
        'course.CourseGrid'
    ],

    models: [
        'Course'
    ],

    stores: [
        'Course'
    ],

    init: function() {

        this.control({
        });

    },

    initialize: function() {
    },

    save: function() {
    }

});

