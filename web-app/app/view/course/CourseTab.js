Ext.define('Course.view.course.CourseTab', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.coursetab',
    id: 'coursetab',
    closable: false,
    title: 'Courses',
    closeAction: 'hide',

    items: [
        {
            xtype: 'courseform'
        },
//        {
//            xtype: 'grid'
//        },
//        {
//            xtype: 'grid'
//        }
    ]
});