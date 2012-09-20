Ext.define('Course.view.course.MyCourseGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.mycoursegrid',
    id: 'mycoursegrid',

    title: 'My Courses',
    store : 'MyCourse',

    columnLines: true,
    height: 360,
    autoScroll: true,

    columns: [
        {
            xtype: 'gridcolumn',
            text: 'Dept',
            dataIndex: 'department',
            flex    : 1
        },
        {
            xtype: 'gridcolumn',
            text: 'Number',
            dataIndex: 'identifier',
            flex    : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'Name',
            dataIndex: 'name',
            flex    : 1
        },
        {
            xtype: 'gridcolumn',
            text: 'Start Time',
            dataIndex: 'startTime',
            flex    : 1
        },
        {
            xtype: 'gridcolumn',
            text: 'End Time',
            dataIndex: 'endTime',
            flex    : 1
        },
        {
            xtype: 'gridcolumn',
            text: 'Days',
            dataIndex: 'days',
            flex : 0.75
        },
        {
            xtype: 'gridcolumn',
            text: 'Credits',
            dataIndex: 'credits',
            flex : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'Prereq',
            dataIndex: 'prerequisite',
            flex : 1
        },
        {
            xtype: 'gridcolumn',
            text: 'Location',
            dataIndex: 'location',
            flex : 1
        },
        {
            xtype: 'gridcolumn',
            text: 'Instructor',
            dataIndex: 'instructor',
            flex : 1
        }

    ],

    dockedItems: [
        {
            xtype: 'pagingtoolbar',
            id: 'mycoursepagingtoolbar',
            store: 'MyCourse',
            autoDestroy : true,
            dock: 'bottom',
            displayInfo: true
        }
    ],

    initComponent: function() {
        this.callParent(arguments);
        this.down('#mycoursepagingtoolbar').down('#refresh').hide();
    }

});