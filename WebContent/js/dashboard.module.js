/**
 * 
 */

var dashboardModule = angular.module("dashboard.module");

dashboardModule.controller('dashboardController', function($scope,$rootScope,$location,dashboardService) {
	
	var dashCtrl = this;
	
	dashCtrl.messageDash = "This is Dashboard";
	
	$scope.init = function () {
		$scope.products = dashboardService.listOfProduct($rootScope.globals.userSession.id,callbackDashboardListSuccess,callbackDashboardListError);
	
	};
	
	dashCtrl.addResume = function () {
		console.log('Add Resume');
		$location.path('/resume/add');
		
	};
	
	dashCtrl.quantity;
	dashCtrl.PROD={
			name:"",
			quantityOrdered:0
			
	};
	dashCtrl.Cart = function (userS) {
		
		$rootScope.customerId = userS.userSession.id;		
		$location.path('/Cart');
		
	};
	
	dashCtrl.getProduct = function () {
		
		$location.path('/ViewTopProduct');
		
	};
	
	
	dashCtrl.addtocart = function(userS,product){
		alert('Product Added to Cart');
		dashCtrl.PROD.name=product.product.name;
		dashCtrl.PROD.quantityOrdered=dashCtrl.quantity;
		$rootScope.customerId = userS.userSession.id;
		
		dashboardService.addtocart(dashCtrl.PROD,$rootScope.customerId,callbackDashboardListSuccess,callbackDashboardListError);
		$location.path('/dashboard');
	};
	
	dashCtrl.getNumber = function(num) {
		alert(num.product.quantityAvail);
		return num.product.quantityAvail;   
	};
	dashCtrl.Orders = function (userS) {
		
		$rootScope.customerId = userS.userSession.id;
		$location.path('/Orders');
		
	};

	dashCtrl.viewResume = function (resumeObj) {
		console.log(resumeObj.id);
		$location.path('/resume/view');

	};
	

	var callbackDashboardListSuccess = function(data,headers) { // Status Code:200
		$scope.products = data;
		
		console.log($scope.products);
		
    };
    
    var callbackDashboardListError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
	
});

dashboardModule.service('dashboardService', function($http,$timeout,APP_CONSTANT) {
	var dashboardService = {};
	
	dashboardService.listOfProduct =  function(id, callback, callbackError) {
			if (APP_CONSTANT.DEMO) {
				console.log('ID -->'+id);
				/*
				 * Dummy authentication for testing, uses $timeout to simulate api
				 * call ----------------------------------------------
				 */
				$timeout(function() {

					var response;
					
					response = [
										{productId:12,name:"Coming to US"},
										{productId:23,name:"After Semester 1"},
										{productId:43,name:"For Co-op"}
								];
					

					callback(response);
				}, 1000);
			} else {

				/*
				 * Use this for real authentication
				 * ----------------------------------------------
				 */
				$http.get(APP_CONSTANT.REMOTE_HOST + '/customer/'+id+'/ViewAllProducts')
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
		
		
		
		dashboardService.addtocart =  function(data,customerId, callback, callbackError) {
			{
				$http.post(APP_CONSTANT.REMOTE_HOST + '/customer/'+customerId+'/AddToCart',data)
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
			
	
	return dashboardService;
})
