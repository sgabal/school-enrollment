Ext.Loader.setPath('Ext.ux', 'js/extjs-4.0.7/src/ux');
Ext.Loader.setConfig({ enabled: true });

Ext.require([
    'Ext.ux.statusbar.StatusBar'
]);

Ext.application({
    name: 'Classes',
 
    appFolder: 'app',
    
    controllers : [
    ],
 
    launch: function() {
    	console.log ('Launching Application...');
    }
});