/**
 * 
 */
var viewSellerProductsModule=angular.module("viewSellerProducts.module");
viewSellerProductsModule.controller('viewSellerProductController', function($scope,$rootScope,$location,viewSellerProdModuleService) {

	var viewSellerProdCntrl = this;
	
	$scope.init = function () {
		$scope.products = viewSellerProdModuleService.viewSellerProds($rootScope.globals.userSession.id,callbackProdListSuccess,callbackProdListError);
		//viewSellProdModuleService.viewCusts(callbackProdListSuccess,callbackProdListError);
	   
	};


	
	var callbackProdListSuccess = function(data,headers) { // Status Code:200
		$scope.products = data;
		
	};
    
    var callbackProdListError = function(data,headers) {
    		$scope.message = data.message;
    		$scope.error = true;   
    };
    
    viewSellerProdCntrl.back = function(){
    	$location.path("/SellerHome");
    };
    


});

viewSellerProductsModule.service('viewSellerProdModuleService', function($http,$timeout,APP_CONSTANT) {
		var viewSellerProdModuleService = {};
		
		
		viewSellerProdModuleService.viewSellerProds =  function( sellerId,callback, callbackError) {
			 {

			
				
				$http.get(APP_CONSTANT.REMOTE_HOST + '/seller/' +sellerId+ '/ViewProducts')
				
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
		
		return viewSellerProdModuleService;
	})