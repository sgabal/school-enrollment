Ext.define('Course.model.MyCourse', {
    extend: 'Course.model.Course',

    fields: [
    ],

    validations: [
    ],

    proxy: {
        type: 'rest',
        url: '/school-enrollment/mycourses',
        reader: {
            type: 'json',
            root: 'mycourses'
        }
    }


});



