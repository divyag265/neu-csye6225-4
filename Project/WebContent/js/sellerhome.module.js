/**
 * 
 */
var sellerhomeModule = angular.module("sellerhome.module");

sellerhomeModule.controller('sellerhomeController', function($scope,$rootScope,$location,sellerhomeService) {
	
	var sellerhomeCtrl = this;
	$scope.messageDash = "This is Admin Home";
	
	sellerhomeCtrl.ship={
			orderitemId:"",
			status:""
	};
	
	sellerhomeCtrl.ship1 ={
			status:""
	};
	
	$scope.init = function () {
		
		$scope.orderitems = sellerhomeService.listOfOrderitems($rootScope.globals.userSession.id,callbackSellerHomeSuccess,callbackSellerHomeError);
		
	};
	
	sellerhomeCtrl.addProduct = function () {
		console.log('Add Product');		
		$location.path('/seller/productReg');
		
	};
	
	sellerhomeCtrl.viewProducts = function () {	
		
		$location.path('/seller/viewProducts');
		
	};
	

	sellerhomeCtrl.markAsShipped= function (orderitem) {
	
		
	sellerhomeCtrl.ship.orderitemId=orderitem.orderitem.orderitemId;
  
	sellerhomeService.markShipped(sellerhomeCtrl.ship.orderitemId,sellerhomeCtrl.ship1,callbackSellerHomeSuccess,callbackSellerHomeError);
//	  $location.path("/AdminHome");
		$location.path('/SellerHome');

	};
	
	var callbackSellerHomeSuccess = function(data,headers) { // Status Code:200
		$scope.orderitems = data;
		console.log($scope.orderitems);
    };
    
    var callbackSellerHomeError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
	
});

sellerhomeModule.service('sellerhomeService', function($http,$timeout,APP_CONSTANT) {
	var sellerhomeService = {};
	
	sellerhomeService.listOfOrderitems =  function(id, callback, callbackError) {
		 {

		
			
			$http.get(APP_CONSTANT.REMOTE_HOST + '/seller/'+id+'/ViewOrderitems')
			
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
	
	sellerhomeService.markShipped =  function(orderitemId,abc, callback, callbackError) {
			 {
				
			$http.put(APP_CONSTANT.REMOTE_HOST + '/seller/'+orderitemId+'/ship',abc)
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
		

		
		return sellerhomeService;;
})