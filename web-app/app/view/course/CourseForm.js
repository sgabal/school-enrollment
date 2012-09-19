Ext.define('Course.view.course.CourseForm', {
    extend: 'Ext.form.Panel',
    id: 'courseform',
    alias: 'widget.courseform',

    autoScroll: true,
    items: [
    {
        xtype: 'panel',
        layout: 'hbox',
        defaults: {
            margin: '5 5 5 5'
        },
        items: [
            {
                xtype: 'combobox',
                id: 'term',
                editable: false,
                fieldLabel: 'Term',
                labelWidth: 60,
                displayField : 'name',
                valueField : 'name',
                flex: 1,
                store: Ext.create('Ext.data.ArrayStore', { fields:['name'], data:[ ['Fall 2012'], ['Spring 2013'] ] })
            },
            {
                xtype: 'combobox',
                id: 'subject',
                editable: false,
                fieldLabel: 'Subject',
                labelWidth: 60,
                displayField : 'name',
                valueField : 'name',
                flex: 1,
                store: Ext.create('Ext.data.ArrayStore', { fields:['name'], data:[ ['Mathematics'], ['Apparel Design'] ] })
            },
            {
                flex: 2,
                border: false
            }
        ]
    }
    ]

});

