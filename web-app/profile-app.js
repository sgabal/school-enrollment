Ext.Loader.setPath('Ext.ux', 'js/extjs-4.0.7/src/ux');
Ext.Loader.setConfig({ enabled: true });

Ext.require([
    'Ext.ux.statusbar.StatusBar'
]);

Ext.application({
    name: 'Profile',
 
    appFolder: 'app',
    
    controllers : [
        'Student'
    ],
 
    launch: function() {
    	console.log ('Launching Application...');

        Ext.create('Ext.panel.Panel', {
            id: 'profile-panel',
            layout: 'fit',
            flex: 4,
            renderTo: 'profile-parent',
            items: [
                {
                    xtype: 'studentpanel'
                }
            ]
        });
    }
});