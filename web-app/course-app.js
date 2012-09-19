Ext.Loader.setPath('Ext.ux', 'js/extjs-4.0.7/src/ux');
Ext.Loader.setConfig({ enabled: true });

Ext.application({
    name: 'Course',
 
    appFolder: 'app',
    
    controllers : [
        'MyCourse',
        'Course'
    ],

    requires: [
        'Course.view.course.MyCourseTab',
        'Course.view.course.CourseTab',
        'Ext.ux.statusbar.StatusBar',
        'Ext.util.Cookies'
    ],

    launch: function() {
        console.log ('Launching Course Application...');

        Ext.create('Ext.tab.Panel', {
            id: 'coursetabs',
            renderTo: 'course-parent',
            items: [
                {
                    xtype: 'mycoursetab'
                },
                {
                    xtype: 'coursetab'
                }
            ],
            dockedItems: [
                {
                    xtype: 'statusbar',
                    id: 'statusbar',
                    dock: 'bottom',
                    defaultText: 'Ready',
                    border: true,
                    width: '100%'
                }
            ]
        });
    }
});