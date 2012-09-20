Ext.define('Profile.controller.Student', {
    extend: 'Ext.app.Controller',

    refs: [
        { ref: 'studentPanel', selector: '#studentpanel'},
        { ref: 'statusBar', selector: '#statusbar'}

    ],

    views: [
        'student.StudentPanel'
    ],

    models: [
        'Student'
    ],

    stores: [
        'Student'
    ],

    init: function() {

        this.control({
            'studentpanel': {
                afterrender: this.initialize
            },
            'studentpanel button[text="Save"]': {
                click: this.save
            }
        });

    },

    initialize: function() {
        Profile.model.Student.load(0, {
            scope: this,
            success: function(student, operation) {
                var studentForm = this.getStudentPanel().getForm();
                studentForm.loadRecord(student)
                studentForm.isValid()
            },
            failure: function(record, operation) {
                var studentForm = this.getStudentPanel().getForm();
                var student = Ext.create('Profile.model.Student');
                studentForm.loadRecord(student)
                studentForm.isValid()
            }
        });

    },

    save: function() {
        var studentForm = this.getStudentPanel().getForm();

        if (!studentForm.isValid()) {
            this.getStatusBar().setError('The Student form contains errors');
            return;
        }

        var student = studentForm.getRecord()
        studentForm.updateRecord(student)

        student.save ({
            scope: this,
            success: function(student) {
                this.getStatusBar().setText('Save was successful');
            },
            failure: function(record, operation) {
                this.getStatusBar().setError('Save resulted in errors: ' + operation.getAllErrors())
            }
        });

    }

});

