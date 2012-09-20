Ext.define('Course.store.MyCourse', {
    extend: 'Ext.data.Store',
    autoLoad: false,
    model: 'Course.model.Course',
    pageSize: 20
});
