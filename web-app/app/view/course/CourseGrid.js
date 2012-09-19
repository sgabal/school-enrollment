Ext.define('Course.view.course.CourseGrid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.coursegrid',
    id: 'coursegrid',

    title: 'Courses',
//    store : 'Course',

    columnLines: true,
    height: 315,
    autoScroll: true,

    columns: [
        {
            xtype: 'gridcolumn',
            text: 'Department',
            dataIndex: 'department',
            flex    : 0.5
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
            flex    : 1.5
        },
        {
            xtype: 'gridcolumn',
            text: 'Start Time',
            dataIndex: 'startTime',
            flex    : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'End Time',
            dataIndex: 'endTime',
            flex    : 1
        },
        {
            xtype: 'gridcolumn',
            text: 'Start Date',
            dataIndex: 'startDate',
            flex : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'End Date',
            dataIndex: 'endDate',
            flex : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'Prerequisites',
            dataIndex: 'prerequisite',
            flex : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'Location',
            dataIndex: 'location',
            flex : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'Instructor',
            dataIndex: 'instructor',
            flex : 0.5
        },
        {
            xtype: 'gridcolumn',
            text: 'Status',
            dataIndex: 'status',
            flex : 0.5
        },
    ],

    dockedItems: [
        {
            xtype: 'pagingtoolbar',
            id: 'pagingtoolbar',
//            store: 'Course',
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