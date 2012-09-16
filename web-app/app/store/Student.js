Ext.define('SchoolEnrollment.store.Student', {
    extend: 'Ext.data.Store',
    autoLoad: false,
    model: 'SchoolEnrollment.model.Student',
    pageSize: 5
});
