Ext.define('SchoolEnrollment.model.Student', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id', type: 'int', useNull:true},
        {name: 'firstName', type:'string'},
        {name: 'lastName', type:'string'},
        {name: 'middleName', type:'string'},
        {name: 'birthDate', type:'string'},
        {name: 'gender', type:'string'},
        {name: 'emailAddress', type:'string'}
    ],

    proxy: {
        type: 'rest',
        url: '/school-enrollment/student',
        reader: {
            type: 'json',
            root: 'data'
        },
        extraParams: {
            search: ''
        }
    }


});



