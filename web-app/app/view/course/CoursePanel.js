Ext.define('Course.view.course.CoursePanel', {
    extend: 'Ext.form.Panel',
    id: 'coursepanel',
    alias: 'widget.coursepanel',

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
                id: 'termcombbox',
                editable: false,
                allowBlank: false,
                fieldLabel: 'Term',
                labelWidth: 60,
                displayField : 'name',
                valueField : 'name',
                flex: 1,
                store: Ext.create('Ext.data.ArrayStore', { fields:['name'], data:[ ['Fall 2012'], ['Spring 2013'] ] })
            },
            {
                xtype: 'combobox',
                id: 'subjectcombobox',
                editable: false,
                allowBlank: false,
                fieldLabel: 'Subject',
                labelWidth: 60,
                displayField : 'name',
                valueField : 'name',
                flex: 1,
                store: Ext.create('Ext.data.ArrayStore', { fields:['name'], data:[ ['Mathematics'], ['Computer Engineering'] ] })
            },
            {
                flex: 2,
                border: false
            }
        ]
    }
    ],

    tbar: [
        {
            xtype:'tbtext',
            text:'Search Courses',
            baseCls: 'x-panel-header-text'
        },
        { xtype:'tbfill'},
        '-',
        {
            xtype: 'button',
            text:'Search',
            itemId:'searchButton'
        },
        '-',
        {
            xtype: 'button',
            text:'Clear',
            itemId:'clearButton'
        }
    ]

});

