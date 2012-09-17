Ext.Loader.setPath('Ext.ux', 'js/extjs-4.0.7/src/ux');
Ext.Loader.setConfig({ enabled: true });

Ext.require([
    'Ext.ux.statusbar.StatusBar'
]);

Ext.application({
    name: 'SchoolEnrollment',
 
    appFolder: 'app',
    
    controllers : [
        'Student'
    ],
 
    launch: function() {
    	console.log ('Launching Application...');

        Ext.create('Ext.panel.Panel', {
            id: 'school-enrollment-panel',
            layout: 'fit',
            flex: 3,
            renderTo: 'school-enrollment-parent',
            items: [
                {
                    xtype: 'studentpanel'
                }
            ]
        });
    }
});