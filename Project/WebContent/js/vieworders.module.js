/**
 * 
 */

var ordermodule=angular.module("vieworders.module");
ordermodule.controller('ViewOrderController', function($scope,$rootScope,$location,vieworderModuleService) {

	$scope.init = function () {
		var customerid = $rootScope.customerId;
		
		
		vieworderModuleService.viewOrders(customerid,callbackOrderListSuccess,callbackOrderListError);
		
		
	};
	
	var callbackOrderListSuccess = function(data,headers) { // Status Code:200
		$scope.orders = data;
		
	};
    
    var callbackOrderListError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
    
     $scope.back = function(){
    	$location.path("/dashboard");
    };

});

ordermodule.service('vieworderModuleService', function($http,$timeout,APP_CONSTANT) {
		var vieworderModuleService = {};
		
		vieworderModuleService.viewOrders =  function(customerId, callback, callbackError) {
				 {

					/*
					 * Use this for real authentication
					 * ----------------------------------------------
					 */
					
					$http.get(APP_CONSTANT.REMOTE_HOST + '/customer/'+customerId+'/ViewOrders')
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
			
		
		return vieworderModuleService;
	})