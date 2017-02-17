/**
 * 
 */
var viewCustomerModule=angular.module("viewCustomers.module");
viewCustomerModule.controller('viewCustController', function($scope,$rootScope,$location,viewCustModuleService) {

	var viewCustCtrl = this;
	
	$scope.init = function () {
		
		$scope.customers = viewCustModuleService.viewCusts(callbackCustListSuccess,callbackCustListError);
		//viewCustModuleService.viewCusts(callbackCustListSuccess,callbackCustListError);
	   
	};
	
	viewCustCtrl.cust={
			customerId:"",
//			name:"",
//			userName:"",
//			email:"",
//			streetAddress:"",
//			city:"",
//			state:"",
//			gender:""
			
	};
	
	viewCustCtrl.deleteCust = function(customer){
		
		alert(customer.customer.customerId);
		viewCustCtrl.cust.customerId=customer.customer.customerId;
	  
	  viewCustModuleService.deleteCust(viewCustCtrl.cust.customerId,callbackCustListSuccess,callbackCustListError);
	  $location.path("/AdminHome");
  };
	
	var callbackCustListSuccess = function(data,headers) { // Status Code:200
		$scope.customers = data;
		
	};
    
    var callbackCustListError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
    
    viewCustCtrl.back = function(){
    	$location.path("/AdminHome");
    };
    


});

viewCustomerModule.service('viewCustModuleService', function($http,$timeout,APP_CONSTANT) {
		var viewCustModuleService = {};
		
		
		viewCustModuleService.viewCusts =  function( callback, callbackError) {
			 {

			
				
				$http.get(APP_CONSTANT.REMOTE_HOST + '/Admin/ViewAllCustomers')
				
				.success(function(data, status, headers, config) {
					callback(data, headers);
				}).error(function(data, status, headers, config) { // IF
					// STATUS
					// CODE
					// NOT
					// 200
					callbackError(data, headers);
				});
			}

		};
		
		viewCustModuleService.deleteCust =  function(customerId, callback, callbackError) {
				 {

				$http.delete(APP_CONSTANT.REMOTE_HOST + '/Admin/'+customerId+'/deleteCustomer')
					// On Success of $http call
					.success(function(data, status, headers, config) {
						callback(data, headers);
					}).error(function(data, status, headers, config) { // IF
						// STATUS
						// CODE
						// NOT
						// 200
						callbackError(data, headers);
					});
				}

			};
			
	
			
		return viewCustModuleService;
	})