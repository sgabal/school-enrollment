Ext.define('Course.store.Course', {
    extend: 'Ext.data.Store',
    autoLoad: false,
    model: 'Course.model.Course',
    pageSize: 20
});
