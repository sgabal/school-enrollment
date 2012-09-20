Ext.define('Course.model.Enrollment', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id', type: 'int', useNull:true},
        {name: 'identifier', type:'string'},
        {name: 'userName', type:'string'}
    ],

    validations: [
    ],

    proxy: {
        type: 'rest',
        url: '/school-enrollment/enrollments',
        reader: {
            type: 'json',
            root: 'enrollments'
        }
    }


});



