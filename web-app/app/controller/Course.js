Ext.define('Course.controller.Course', {
    extend: 'Ext.app.Controller',

    refs: [
        {ref: 'coursePanel', selector: '#coursepanel'},
        {ref: 'termComboBox', selector: '#termcombbox'},
        {ref: 'subjectComboBox', selector: '#subjectcombobox'},
        { ref: 'statusBar', selector: '#statusbar'}
    ],

    views: [
        'course.CoursePanel',
        'course.CourseGrid'
    ],

    models: [
        'Course'
    ],

    stores: [
        'Course'
    ],

    init: function() {

        this.control({
            'coursepanel': {
                afterrender: this.initialize
            },
            'coursepanel button[text="Search"]': {
                click: this.search
            },
            'coursepanel button[text="Clear"]': {
                click: this.clear
            },
            'coursegrid': {
                itemcontextmenu: this.showMenu
            }
        });

    },

    initialize: function() {
    },

    search: function() {
       if (!this.getCoursePanel().getForm().isValid()) {
           this.getStatusBar().setError('Search form contains errors')
           return
       }

        this.getCourseStore().load({
            scope: this,
            params :{
                start:0,
                page:1,
                term: this.getTermComboBox().getValue(),
                subject: this.getSubjectComboBox().getValue()
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

    clear: function() {
        this.getCoursePanel().getForm().reset();
        this.getCourseStore().removeAll();
        this.getStatusBar().clearStatus();
    },

    showMenu: function() {

    }

});

