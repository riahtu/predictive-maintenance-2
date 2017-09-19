sap.ui.define([
	"sap/ui/core/mvc/Controller"
], function(Controller) {
	"use strict";

	return Controller.extend("Test.controller.Main", {
		onInit : function(){
			this.loadAllInvoices();
		},
		
		onFilterInvoices : function(oEvent){
			var sQuery = this.getView().byId("companyName").getValue();
			// build filter array
			var that  = this;
			$.ajax({

			    url : 'http://localhost:3001/getMyBlocks?companyName='+sQuery,
			    type : 'GET',
			    dataType:'json',
			    crossDomain:true,
			    success : function(data) {              
			        console.log(data);
			        if(data !== ""){
			        	that.getView().byId("invoicesTable").setModel(new sap.ui.model.json.JSONModel({"blocks" : data}), "invoices");
			        }
			    },
			    error : function(request,error)
			    {
			        alert("Error: "+JSON.stringify(request));
			    }
			});
		},
		
		onResetFilter : function(){
			this.getView().byId("companyName").setValue("");
			this.loadAllInvoices();
		},
		
		loadAllInvoices : function(){
			var that = this;
			$.ajax({

			    url : 'http://localhost:3001/blocks',
			    type : 'GET',
			    dataType:'json',
			    crossDomain:true,
			    success : function(data) {              
			        console.log(data);
			        data.splice(0,1);
			        if(data !== ""){
			        	that.getView().byId("invoicesTable").setModel(new sap.ui.model.json.JSONModel({"blocks" : data}), "invoices");
			        }
			    },
			    error : function(request,error)
			    {
			        alert("Error: "+JSON.stringify(request));
			    }
			});
			
		}
	});
});