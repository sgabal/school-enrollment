Ext.define('Profile.model.Enrollment', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id', type: 'int', useNull:true},
        {name: 'number', type:'string'},
        {name: 'userName', type:'string'}
    ],

    validations: [
        {type: 'presence', field: 'firstName', message: 'First Name is required'},
        {type: 'presence', field: 'lastName', message: 'Last Name is required'},
        {type: 'presence', field: 'gender', message: 'Gender is required'},
        {type: 'email', field: 'email', message: 'Email is invalid'}
    ],

    proxy: {
        type: 'rest',
        url: '/school-enrollment/students',
        reader: {
            type: 'json',
            root: 'students'
        }
    }


});



