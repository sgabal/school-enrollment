Ext.Loader.setPath('Ext.ux', 'js/extjs-4.0.7/src/ux');
Ext.Loader.setPath('Extensible', 'js/extensible-1.5.1/src');
Ext.Loader.setConfig({ enabled: true });

Ext.require([
    'Extensible.calendar.CalendarPanel'
]);

Ext.application({
    name: 'Calendar',
 
    appFolder: 'app',
    
    controllers : [
    ],

    requires: [
        'Ext.ux.statusbar.StatusBar'
    ],

    launch: function() {
        console.log ('Launching Calendar Application...');

        var eventStore = Ext.create('Extensible.calendar.data.MemoryEventStore', {
        });

        Ext.create('Extensible.calendar.CalendarPanel', {
            eventStore: eventStore,
            renderTo: 'calendar-parent',
            title: 'Basic Calendar',
            width: 960,
            height: 415
        });

    }
});