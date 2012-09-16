Ext.define('SchoolEnrollment.view.student.StudentPanel', {

    extend:'Ext.panel.Panel',
    alias:'widget.studentpanel',

    requires:[
//        'FOW.view.common.StatusBar'
    ],

    border:true,
    layout:'border',
    preventHeader:true,
    height:685,

    items:[
//        {
//            region:'north',
//            xtype:'apoheader',
//            border:false,
//            collapsible:true,
//            height:295,
//            width:'100%'
//        },
//        {
//            region:'center',
//            xtype:'apotabs',
//            border:false,
//            width:'100%'
//        },
//        {
//            region:'south',
//            xtype:'fowstatusbar',
//            border:false,
//            width:'100%'
//        }
    ],

    initComponent:function () {
        console.log('Creating Panel')
        this.callParent(arguments);
    }
});

