Ext.Loader.setPath('Ext.ux', 'js/extjs-4.0.7/src/ux');
Ext.Loader.setConfig({ enabled: true });
Ext.require([
     'Ext.data.*',
     'Ext.grid.*',
     'Ext.ux.CheckColumn',
     'Ext.ux.GroupingCheckbox',
     'Ext.ux.form.MultiSelect',
]);


Ext.application({
    name: 'SchoolEnrollment',
 
    appFolder: 'app',
    
    controllers : [
    ],
 
    launch: function() {
    	console.log ('Launching Application...');

        Ext.create('Ext.panel.Panel', {
            id: 'school-enrollment-panel',
            layout: 'fit',
            flex: 3,
            renderTo: 'school-enrollment-parent',
            items: [
//                {xtype: 'studentpanel'}
                {
                    xtype: 'textfield',
                    name: 'flyerId',
                    itemId: 'flyerid',
                    fieldLabel: 'Flyer Part #'
                }
            ]
        });
    }
});