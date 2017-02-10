/**
 * 
 */

var topProductModule = angular.module("topProduct.module");

topProductModule.controller('topProductController', function($scope,$rootScope,$location,topProductService) {
	
	var topProdCntrl = this;
	
	
	$scope.init = function () {
		$scope.product = topProductService.listOfProduct(callbackDashboardListSuccess,callbackDashboardListError);
	
	};
	
	
	topProdCntrl.back = function () {
		
		$location.path('/dashboard');

	};
	

	var callbackDashboardListSuccess = function(data,headers) { // Status Code:200
		$scope.product = data;
	
    };
    
    var callbackDashboardListError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
	
});

topProductModule.service('topProductService', function($http,$timeout,APP_CONSTANT) {
	var topProductService = {};
	
	
		
		
	topProductService.listOfProduct =  function( callback, callbackError) {
			{
				$http.get(APP_CONSTANT.REMOTE_HOST + '/customer/ViewTopSoldProduct')
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
			
	
	return topProductService;
})
