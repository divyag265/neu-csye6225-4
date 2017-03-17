/**
 * 
 */
'use strict';
// Step 1: declare modules
 angular.module("authModule",[]);
 angular.module("base.module",[]);
 angular.module("welcome.module",[]);
 angular.module("registration.module",[]);
 angular.module("dashboard.module", []);
 angular.module("resume.module", []);
 angular.module("viewcart.module", []);
 angular.module("vieworders.module", []);
 angular.module("adminhome.module", []);
 angular.module("sellerReg.module", []);
 angular.module("viewCustomers.module", []);
 angular.module("sellerhome.module", []);
 angular.module("sellerReg.module", []);
 angular.module("productReg.module", []);
 angular.module("viewSellerProducts.module", []);
 angular.module("topProduct.module", []);
 
 angular
 .module('appCoreModule', [
	 'ngRoute',
     'ngCookies',
     'ui.bootstrap'
 ]);
//Step 2: Register App
angular.module("app", 	
			['appCoreModule',
			'welcome.module',
			'registration.module',
			'authModule',
			'dashboard.module',
			'base.module',
			'resume.module',
			'viewcart.module',
			'vieworders.module',
			'adminhome.module',
			'sellerReg.module',
			'viewCustomers.module',
			'sellerhome.module',
			'sellerReg.module',
			'productReg.module',
			'viewSellerProducts.module',
			'topProduct.module'
			
		 ]);
