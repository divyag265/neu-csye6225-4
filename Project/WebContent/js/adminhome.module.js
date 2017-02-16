/**
 * 
 */

var adminhomeModule = angular.module("adminhome.module");

adminhomeModule.controller('adminhomeController', function($scope,$rootScope,$location,adminhomeService) {
	
	$scope.messageDash = "This is Admin Home";
	
	$scope.init = function () {
		$scope.sellers = adminhomeService.listOfSellers($rootScope.globals.userSession.id,callbackAdminHomeSuccess,callbackAdminHomeError);
		
	};
	
	$scope.addSeller = function () {
		console.log('Add Seller');
		$location.path('/admin/sellerReg');
		
	};
	
	$scope.viewCustomers = function () {
		
		$location.path('/admin/viewCustomers');
		
	};
	

	$scope.deleteSeller = function (resumeObj) {
		console.log(resumeObj.id);
		$location.path('/resume/view');

	};
	
	var callbackAdminHomeSuccess = function(data,headers) { // Status Code:200
		$scope.sellers = data;
		console.log($scope.sellers);
    };
    
    var callbackAdminHomeError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
	
});

adminhomeModule.service('adminhomeService', function($http,$timeout,APP_CONSTANT) {
	var adminhomeService = {};
	
	adminhomeService.listOfSellers =  function(id, callback, callbackError) {
			if (APP_CONSTANT.DEMO) {
				console.log('ID -->'+id);
				/*
				 * Dummy authentication for testing, uses $timeout to simulate api
				 * call ----------------------------------------------
				 */
				$timeout(function() {

					var response;
					
					response = [
										{sellerId:12,name:"Coming to US"},
										{sellerId:23,name:"After Semester 1"},
										{sellerId:43,name:"For Co-op"}
								];
					

					callback(response);
				}, 1000);
			} else {

				/*
				 * Use this for real authentication
				 * ----------------------------------------------
				 */
				$http.get(APP_CONSTANT.REMOTE_HOST + '/Admin/'+id+'/ViewAllSellers')
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
			
	return adminhomeService;
})
