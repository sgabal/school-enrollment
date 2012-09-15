Ext.Loader.setPath('Ext.ux', 'extjs/src/ux');
Ext.Loader.setConfig({ enabled: true });
Ext.require([
             'Ext.data.*',
             'Ext.grid.*',
             'Ext.ux.CheckColumn',
             'Ext.ux.GroupingCheckbox',
             'Ext.ux.form.MultiSelect'
         ]);


Ext.application({
    name: 'SchoolEnrollment',
 
    appFolder: 'app',
    
    controllers : [
    ],
 
    launch: function() {
    	console.log ('Launching Application...');
    }
});