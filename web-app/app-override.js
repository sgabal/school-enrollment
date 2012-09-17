Ext.Loader.setPath('Ext.ux', 'js/extjs-4.0.7/src/ux');
Ext.Loader.setConfig({ enabled: true });

Ext.require([
    'Ext.data.*',
    'Ext.grid.*'
]);


Ext.onReady(function() {

	// Look for errors (from ErrorsController) and place them into Ext.data.Operation
	Ext.util.Observable.observe(Ext.data.proxy.Rest);
	Ext.data.proxy.Rest.on('exception', function(proxy, response, operation){
        var json = Ext.decode(response.responseText);
        if (json && json.errors) {
            operation.setException(json.errors);
        }		
	});	
	
	// this override contains helper methods to interogate field, object, and fault
	// errors returned from the grails ErrorsController
	Ext.data.Operation.implement({
		hasFieldError: function(field) {
			var errors = this.getError();
			if (errors) {
				for (index = 0; index < errors.length; index++) {
					if (errors[index].field == field) {
						return true;
					} 
				}
			}    			
			return false;
		},    		
		getFieldError: function(field) {
			var errors = this.getError();
			if (errors) {
				for (index = 0; index < errors.length; index++) {
					if (errors[index].field == field) {
						return errors[index].message;
					} 
				}
			}    			
			return null;
		},
		getFieldErrors: function(field) {
			var errors = Ext.create('Ext.data.Errors');
			if (this.error) {
				for (index = 0; index < this.error.length; index++) {
					if (this.error[index].field.indexOf(field) === 0) {
	                    errors.add({
	                        field  : this.error[index].field,
	                        message: this.error[index].message
	                    });								
					}
				}
			}
			return errors;    			
		},     		
		getAllFieldErrors: function() {
			var errors = Ext.create('Ext.data.Errors');
			if (this.error) {
				for (index = 0; index < this.error.length; index++) {
					if (this.error[index].field) {
	                    errors.add({
	                        field  : this.error[index].field,
	                        message: this.error[index].message
	                    });							
					}
				}
			}
			return errors;    			
		},       		
		getObjectError: function(object) {
			var errors = this.getError();
			if (errors) {
				for (index = 0; index < errors.length; index++) {
					if (errors[index].object == object) {
						return errors[index].message;
					} 
				}
			}    			
			return null;
		},
		hasFault: function() {
			return this.getFault() != null
		},    		
		getFault: function() {
			var errors = this.getError();
			if (errors) {
				for (index = 0; index < errors.length; index++) {
					if (errors[index].field == undefined && errors[index].object == undefined) {
						return errors[index].message;
					} 
				}
			}    			
			return null;
		},
		hasErrors: function() {
			return this.hasException();
		},
		getAllErrors: function() {
			var messages = [];
			if (this.error) {
				for (index = 0; index < this.error.length; index++) {
					messages.push(this.error[index].message);
				}
			}
			return Ext.Array.unique(messages).join();
		}    		
	});
	
	// overrides to highlight grid errors
	Ext.grid.Panel.implement({
		markCellInvalid: function(row, column, message) {
	        var cell = this.getView().getCellByPosition( {row : row, column : column} );
	        Ext.fly( cell.child( 'div' ) ).addCls( "x-grid-invalid-cell" );
	        cell.set( {'data-errorqtip' : message} );
		},
		markColumnInvalid: function(column, message) {
			for (row = 0; row < this.getStore().getCount(); row++) {
				this.markCellInvalid(row, column, message);
			}
		},
		markRowInvalid: function(row, message) {
			for (column = 0; column < this.columns.length; column++) {
				this.markCellInvalid(row, column, message);
			}
		},
		markInvalid: function(errors) {
	        errors.each( function( error ) {
				var groups = /(\w+)\[(\d+)\]\.{0,1}(\w*)/.exec(error.field)
				
				var row = parseInt(groups[2]);
				var field = groups[3];
				
				if (field) {
					var column = this.indexOf( field );
					this.markCellInvalid(row, column, error.message);	
				} else {
					this.markRowInvalid(row, error.message);	
				}
	        }, this );        			
		},        		
		validate: function() {
	        Ext.each( this.getStore().getRange(), function( model ) {
	            var errors = model.validate();
	            if( !errors.isValid() ) {
	                errors.each( function( error ) {
	                	 var column = this.indexOf( error.field );
	                	 var row = this.getStore().indexOf( model );
	                	 this.markCellInvalid(row, column, error.message);
	                }, this );	                	
	            }
	
	        }, this );      			
		},
	    indexOf:function(field) {
	        var index = null;
	        for (var i = 0; i < this.columns.length; i++) {
	            if (this.columns[i].dataIndex == field) {
	                index = this.columns[i].getIndex();
	                break;
	            }
	        }
	        return index;
	    }    		
	});
	
	// this override solves the bug documented on following web page
	// http://www.mysamplecode.com/2012/01/extjs-combobox-loadmask-second-time.html
	Ext.override(Ext.LoadMask, {
	    onHide: function() {
	         this.callParent(arguments);
	    }
	});

    Ext.override(Ext.form.field.Base, {
        setModelFieldValidation: function(validation) {
            this.modelValidations = Ext.isArray(this.modelValidations) ? this.modelValidations : [];
            this.modelValidations.push(validation);
        },
        getModelErrors: function(value) {
            var errors = Ext.create('Ext.data.Errors'),
                validations = this.modelValidations,
                validators = Ext.data.validations,
                length, validation, field, valid, type, i;

            if (validations) {
                length = validations.length;

                for (i = 0; i < length; i++) {
                    validation = validations[i];
                    field = validation.field || validation.name;
                    type = validation.type;
                    valid = validators[type](validation, value);

                    if (!valid) {
                        errors.add({
                            field : field,
                            message: validation.message || validators[type + 'Message']
                        });
                    }
                }
            }
            return errors;
        },
        validateValue: function(value) {
            var me = this,
                errors = me.getErrors(value),
                modelErrors = me.getModelErrors(value),
                isValid = Ext.isEmpty(errors) && modelErrors.isValid();
            if (!me.preventMark) {
                if (isValid) {
                    me.clearInvalid();
                }
                else {
                    if(!modelErrors.isValid()) {
                        modelErrors.each(function() {
                            errors.push(this.message);
                        })
                    }
                    me.markInvalid(errors);
                }
            }
            return isValid;
        }
    })

    Ext.override(Ext.form.Basic, {
        loadRecord: function(record) {
            this._record = record;
            this.setModelValidations(record.validations);
            return this.setValues(record.data);
        },
        setModelValidations: function(validations) {
            var fields = this.getFields(), i;
            for(i=0;i<validations.length;i++) {
                if(fields.map[validations[i].field]) {
                    fields.map[validations[i].field].setModelFieldValidation(validations[i])
                }
            }
        }
    })

});

