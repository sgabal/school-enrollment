Ext.define('Profile.view.student.StudentPanel', {
    extend:'Ext.form.Panel',
    alias:'widget.studentpanel',
    id: 'studentpanel',

    title: 'Student',
    bodyStyle: 'padding: 5px',
    autoScroll: true,
    defaults: {
        margin: '5 0 5 0'
    },
    border:false,

    items:[
        {
            xtype: 'fieldset',
            title: '',
            collapsible: false,
            defaults: {
                border: false,
                margin: '5 0 5 0'
            },
            items: [
                {
                    xtype: 'fieldcontainer',
                    layout: 'hbox',
                    defaults: {
                        margin: '0 5 0 0'
                    },
                    items: [
                        {xtype: 'textfield',name: 'firstName',id: 'firstName',fieldLabel: 'First Name'},
                        {xtype: 'textfield',name: 'middleName',id: 'middleName',fieldLabel: 'Middle Name'},
                        {xtype: 'textfield',name: 'lastName',id: 'lastName',fieldLabel: 'Last Name'}
                    ]
                },
                {xtype: 'textfield',name: 'email',id: 'email',fieldLabel: 'Email'},
                {xtype: 'combobox', name: 'gender', id: 'gender', fieldLabel: 'Gender',
                    editable : false, displayField : 'name',valueField : 'name',
                    store: Ext.create('Ext.data.ArrayStore', {fields:['name'], data:[['Male'],['Female']]})}
            ]
        },
        {
            xtype: 'fieldset',
            title: 'Address',
            collapsible: true,
            defaults: {
                border: false,
                margin: '5 0 5 0'
            },
            items: [
                {xtype: 'textfield',name: 'street',id: 'street',fieldLabel: 'Street'},
                {xtype: 'textfield',name: 'city',id: 'city',fieldLabel: 'City'},
                {xtype: 'combobox',name: 'state',id: 'state',fieldLabel: 'State',
                    editable : false, displayField : 'name',valueField : 'name',
                    store: Ext.create('Ext.data.ArrayStore', {fields:['name'], data:[['MN'],['WI']]})},
                {xtype: 'textfield',name: 'zipCode',id: 'zipCode',fieldLabel: 'Zip Code'}
            ]
        }

    ],

    buttons : [
        {
            text: 'Save',
            id: 'studentsavebutton',
            formBind: true
        },
        {
            text: 'Cancel',
            id: 'studentcancelbutton'
        }
    ],

    dockedItems: [
        {
            xtype: 'statusbar',
            id: 'statusbar',
            dock: 'bottom',
            defaultText: 'Ready',
            border: true,
            width: '100%'
        }
    ],

    initComponent:function () {
        this.callParent(arguments);
    },

    constructor: function(config) {
        Ext.applyIf(config, {
            trackResetOnLoad:true
        });
        this.callParent(arguments);
    }
});

