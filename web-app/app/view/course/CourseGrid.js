Ext.define('Course.view.course.CourseGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.coursegrid',
    id: 'coursegrid',

    title: 'Courses',
    store : 'Course',

    columnLines: true,
    height: 290,
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
            dataIndex: 'number',
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
        },
        {
            xtype: 'gridcolumn',
            text: 'Status',
            dataIndex: 'status',
            flex : 1
        },
        {
            xtype: 'gridcolumn',
            text: 'Size',
            dataIndex: 'size',
            flex : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'Max',
            dataIndex: 'maxSize',
            flex : 0.5
        }
    ],

    dockedItems: [
        {
            xtype: 'pagingtoolbar',
            id: 'pagingtoolbar',
            store: 'Course',
            autoDestroy : true,
            dock: 'bottom',
            displayInfo: true
        }
    ],

    initComponent: function() {
        this.callParent(arguments);
        this.down('#pagingtoolbar').down('#refresh').hide();
    }

});