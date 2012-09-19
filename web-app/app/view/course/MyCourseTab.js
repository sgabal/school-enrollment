Ext.define('Course.view.course.MyCourseTab', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.mycoursetab',
    id: 'mycoursetab',
    closable: false,
    title: 'My Courses',
    closeAction: 'hide',

    items: [
        {
            xtype: 'panel'
        },
//        {
//            xtype: 'grid'
//        },
//        {
//            xtype: 'grid'
//        }
    ]
});