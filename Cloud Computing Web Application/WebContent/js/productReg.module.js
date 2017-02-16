/**
 * 
 */

var productRegModule = angular.module("productReg.module", []);
productRegModule.controller('productRegController', function($location,
		$scope,$rootScope, $uibModal, productRegService) {

	var productRegCtrl = this;

	productRegCtrl.addProduct = {
		name : "",
		description : "",
		price : "",
		quantityAvail : ""
		
	};
	
	

	productRegCtrl.cancel = function() {
		$location.path('/SellerHome');
	}

	productRegCtrl.back = function() {
		$location.path('/SellerHome');
	}
	
	productRegCtrl.productReg = function() {
		
		console.log(productRegCtrl.addProduct);
		productRegService.productReg($rootScope.globals.userSession.id,productRegCtrl.addProduct, callbackSuccess,
				callbackError);

	}

	productRegCtrl.error = false;
	productRegCtrl.message = "Created successfully";

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		if (data.success) {
			
			alert("Product added successfully");
			$location.path('/SellerHome');

		} else {
			productRegCtrl.message = data.message;
			productRegCtrl.error = true;
		}

	};

	var callbackError = function(data, headers) {
		productRegCtrl.message = data.message;
		productRegCtrl.error = true;
		alert("Couldn't add Product");

	};

	productRegCtrl.openComponentModal = function(msgToDisplay) {
		var modalInstance = $uibModal.open({
			animation : true,
			component : 'successComponent',
			resolve : {
				msg : function() {
					return msgToDisplay;
				}
			}
		});

	};

});

productRegModule.factory('productRegService', function($rootScope, $http,
		$timeout, $cookieStore, $window, APP_CONSTANT, AUTH_EVENTS) {
	var productRegService = {};

	productRegService.productReg = function(sellerId, abc, callback, callbackError) {
		if (APP_CONSTANT.DEMO) {

			/*
			 * Dummy authentication for testing, uses $timeout to simulate api
			 * call ----------------------------------------------
			 */
			$timeout(function() {

				var response;
				if (data.username === 'test' && data.password === 'test') {
					response = {
						success : true,
					};
				} else {
					response = {
						success: false,
						message : 'Registration was not successful'
					};
				}

				callback(response);
			}, 1000);
		} else {

			/*
			 * Use this for real authentication
			 * ----------------------------------------------
			 */
			$http.post(APP_CONSTANT.REMOTE_HOST + '/seller/'+sellerId+'/productReg',abc

			)
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
	
	return productRegService;

});
