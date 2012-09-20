Ext.define('Course.controller.MyCourse', {
    extend: 'Ext.app.Controller',

    refs: [
    ],

    views: [
        'course.MyCourseGrid'
    ],

    models: [
        'MyCourse'
    ],

    stores: [
        'MyCourse'
    ],

    init: function() {

        this.control({
            'mycoursegrid': {
                afterrender: this.initialize
            }
        });

    },

    initialize: function() {
        this.getMyCourseStore().load({
            scope: this,
            params :{
                start:0,
                page:1
            },
            callback: function(records, operation, success) {
                if (success) {
                    this.getStatusBar().clearStatus()
                } else {
                    this.getStatusBar().setError('Search resulted in errors: ' + operation.getAllErrors())
                }
            }
        });
    },

    save: function() {
    }

});

