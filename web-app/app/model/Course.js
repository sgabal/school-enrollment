Ext.define('Course.model.Course', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id', type: 'int', useNull:true},
        {name: 'department', type:'string'},
        {name: 'number', type:'string'},
        {name: 'name', type:'string'},
        {name: 'startTime', type:'string'},
        {name: 'endTime', type:'string'},
        {name: 'days', type:'string'},
        {name: 'credits', type:'int'},
        {name: 'prerequisite', type:'string'},
        {name: 'location', type:'string'},
        {name: 'instructor', type:'string'},
        {name: 'status', type:'string'},
        {name: 'size', type:'int'},
        {name: 'maxSize', type:'int'},
    ],

    validations: [
    ],

    proxy: {
        type: 'rest',
        url: '/school-enrollment/courses',
        reader: {
            type: 'json',
            root: 'courses'
        }
    }


});



