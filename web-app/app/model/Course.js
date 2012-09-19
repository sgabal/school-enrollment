Ext.define('Course.model.Course', {
    extend: 'Ext.data.Model',

    fields: [
        {name: 'id', type: 'int', useNull:true},
        {name: 'department', type:'string'},
        {name: 'number', type:'string'},
        {name: 'name', type:'string'},
        {name: 'startTime', type:'string'},
        {name: 'endTime', type:'string'},
        {name: 'startDate', type:'string'},
        {name: 'endDate', type:'string'},
        {name: 'prerequisite', type:'string'},
        {name: 'location', type:'string'},
        {name: 'instructor', type:'string'},
        {name: 'status', type:'string'},
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



