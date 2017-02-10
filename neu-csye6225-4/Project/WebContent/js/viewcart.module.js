/**
 * 
 */
var cartModule=angular.module("viewcart.module");
cartModule.controller('CartController', function($scope,$rootScope,$location,cartModuleService) {

	var CartCtrl = this;
	
	$scope.init = function () {
		var customerid = $rootScope.customerId;
		cartModuleService.viewCart(customerid,callbackCartListSuccess,callbackCartListError);
	
	};
	
	CartCtrl.checkout = function(){
		
	  var customerid = $rootScope.customerId;
	  cartModuleService.checkout(customerid,callbackCartListSuccess,callbackCartListError);
	  $location.path("/Cart");
  }
	
	var callbackCartListSuccess = function(data,headers) { // Status Code:200
		$scope.carts = data;
		
	};
    
    var callbackCartListError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
    
    CartCtrl.back = function(){
    	$location.path("/dashboard");
    };
    


});

cartModule.service('cartModuleService', function($http,$timeout,APP_CONSTANT) {
		var cartModuleService = {};
		
		cartModuleService.viewCart =  function(customerId, callback, callbackError) {
				 {

					/*
					 * Use this for real authentication
					 * ----------------------------------------------
					 */
					
					$http.get(APP_CONSTANT.REMOTE_HOST + '/customer/'+customerId+'/ViewCart')
					// On Success of $http call
					.success(function(data, status, headers, config) {
						callback(data, headers);
					}).error(function(data, status, headers, config) { // IF
						
						callbackError(data, headers);
					});
				}

			};
			
			
			cartModuleService.checkout = function(customerId, callback, callbackError) {
				{
				$http.post(APP_CONSTANT.REMOTE_HOST + '/customer/'+customerId+'/Checkout')
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
			
		return cartModuleService;
	})