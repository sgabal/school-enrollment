Ext.define('Course.controller.Course', {
    extend: 'Ext.app.Controller',

    refs: [
        {ref: 'coursePanel', selector: '#coursepanel'},
        {ref: 'termComboBox', selector: '#termcombbox'},
        {ref: 'subjectComboBox', selector: '#subjectcombobox'},
        {ref: 'statusBar', selector: '#statusbar'}
    ],

    views: [
        'course.CoursePanel',
        'course.CourseGrid'
    ],

    models: [
        'Course',
        'Enrollment'
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

    showMenu: function(grid, course, item, index, event, eOpts) {

        var me = this;
        event.stopEvent(); // stop browser menu

        if (! grid.getSelectionModel().isSelected(course)) {
            grid.getSelectionModel().select(course, false, true);
        }

        var menu = Ext.create('Ext.menu.Menu', {
            items: [
                {
                    text: 'Enroll',
                    handler: function(menuitem) { me.enroll(course) }
                },
                {
                    text: 'View Details',
                    handler: function(menuitem) {
                    }
                }
            ]
        }, this);
        menu.showAt(event.xy);
    },

    enroll: function(course) {
        var userName = Ext.util.Cookies.get('userName');
        if (!userName) {
            this.getStatusBar().setError('You must setup a student profile before enrolling.')
            return
        }

        var enrollment = Ext.create('Course.model.Enrollment');
        enrollment.set('number', course.get('number'));
        enrollment.set('userName', userName);

        enrollment.save({
            scope: this,
            success: function(enrollment) {
                this.getStatusBar().setText('Enrollment was successful')
            },
            failure: function(record, operation) {
                this.getStatusBar().setError('Enrollment resulted in errors: ' + operation.getAllErrors())
            }
        });

    }

});

