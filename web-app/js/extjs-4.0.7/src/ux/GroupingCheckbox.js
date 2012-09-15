/*
 * Over-ridden version of Grouping functionality in Grid.
 * If you need an input field in the Grouping and do not want to collapse/expand the grouping on clicking the field, use this class.
 */
Ext.define('Ext.ux.GroupingCheckbox', {
	extend: 'Ext.grid.feature.Grouping',
	alias: 'widget.groupingcheckbox',
	
	onGroupClick : function(view, group, idx, foo, e) {
		if (foo.getTarget().tagName == 'INPUT') {
			foo.stopPropagation();
		} 
		else {
			this.callParent(arguments);
		}
	}

});