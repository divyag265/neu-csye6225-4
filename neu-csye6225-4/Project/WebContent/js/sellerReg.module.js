/**
 * 
 */

var sellerRegModule = angular.module("sellerReg.module", []);
sellerRegModule.controller('sellerRegController', function($location,
		$scope, $uibModal, sellerRegService) {

	var sellerCtrl = this;

	sellerCtrl.addSeller = {
		name : "",
		userName : "",
		password : "",
		email : "",
		streetAddress : "",
		city : "",
		state : "",		
		company : ""
	};



	sellerCtrl.cancel = function() {
		$location.path('/AdminHome');
	}

	sellerCtrl.sellerReg = function() {
		console.log(sellerCtrl.addSeller);
		sellerRegService.sellerReg(sellerCtrl.addSeller, callbackSuccess,
				callbackError);

	}

	sellerCtrl.error = false;
	sellerCtrl.message = "Created successfully";

	var callbackSuccess = function(data, headers) { // Status
		// Code:200
		if (data.success) {
			
			alert("Seller added successfully");
			$location.path('/AdminHome');

		} else {
			sellerCtrl.message = data.message;
			sellerCtrl.error = true;
		}

	};

	var callbackError = function(data, headers) {
		sellerCtrl.message = data.message;
		sellerCtrl.error = true;
		alert("Couldn't add seller");

	};

	sellerCtrl.openComponentModal = function(msgToDisplay) {
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

sellerRegModule.factory('sellerRegService', function($rootScope, $http,
		$timeout, $cookieStore, $window, APP_CONSTANT, AUTH_EVENTS) {
	var sellerRegService = {};

	sellerRegService.sellerReg = function(data, callback, callbackError) {
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
			$http.post(APP_CONSTANT.REMOTE_HOST + '/sellerReg', data

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
	
	return sellerRegService;

});
