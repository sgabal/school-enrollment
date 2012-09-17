Ext.define('SchoolEnrollment.controller.Student', {
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
        var student = Ext.create('SchoolEnrollment.model.Student');
        var studentForm = this.getStudentPanel().getForm();
        studentForm.loadRecord(student)
        studentForm.isValid()
    },

    save: function() {
        var studentForm = this.getStudentPanel().getForm();

        if (!studentForm.isValid()) {
//            this.getStatusBar().setError('The Offering form contains errors');
            return;
        }

        var student = studentForm.getRecord()
        studentForm.updateRecord(student)

        student.save ({
            scope: this,
            success: function(student) {
                console.log('success')
                this.getStatusBar().setText('Save was successful')
//                this.refresh();
            },
            failure: function(record, operation) {
//                this.getStatusBar().setError('Publish resulted in errors: ' + operation.getAllErrors())
            }
        });

    }

});

