/**
 * 
 */
var welcomeModule = angular.module("welcome.module");
welcomeModule.controller('welcomeController', function($scope,$rootScope,$location,welcomeService) {
	
	 var welcomeCtrl=this;
			
			$scope.init = function () {
				
				$scope.products = welcomeService.listOfProduct(callbackDashboardListSuccess,callbackDashboardListError);
				
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

welcomeModule.service('welcomeService', function($http,$timeout,APP_CONSTANT) {
			var welcomeService = {};
			
			welcomeService.listOfProduct =  function(callback, callbackError) {
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
						$http.get(APP_CONSTANT.REMOTE_HOST + '/customer/ViewAllProducts')
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
			
					
			
			return welcomeService;
		})
