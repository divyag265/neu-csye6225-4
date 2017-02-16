/**
 * 
 */

var configModule = angular.module('app') // Please dont not use [], dependency 
.config(function($routeProvider) {	
//    $urlRouterProvider.otherwise('/login');
	$routeProvider
    // route for the home page
	.when('/', {
		 templateUrl : 'partial/welcome.html',
	     controller  : 'welcomeController',
	     controllerAs: 'welcomeCtrl'
	})
	.when('/login', {
		 templateUrl : 'partial/login.html',
	     controller  : 'authController',
	     controllerAs: 'authCtrl'
	})
	
	.when('/Cart', {
		templateUrl : 'partial/Cart.html',
	     controller  : 'cartController'
	     
	})
	.when('/registration', {
		 templateUrl : 'partial/registration.html',
	     controller  : 'registrationController',
	     controllerAs: 'regCtrl'
	})
	.when('/logout', {
		redirectTo: '/'
	})
   .when('/dashboard', {
        templateUrl : 'partial/home.html',
        controller  : 'dashboardController',
        	controllerAs: 'dashCtrl'
        		
    })
    .when('/resume/add', {
        templateUrl : 'partial/resume-add.html',
        controller  : 'addResumeController'
    })
    .when('/resume/view', {
        templateUrl : 'partial/personal.html',
        controller  : 'personalController'
    })
    .when('/skill', {
        templateUrl : 'partial/skill.html',
        controller  : 'skillContoller',
        controllerAs: 'skillCtrl'
    })
    .when('/personal', {
        templateUrl : 'partial/personal.html',
        controller  : 'personalContoller',
        controllerAs: 'personalCtrl'
    })
    .when('/Cart', {
        templateUrl : 'partial/Cart.html',
        controller  : 'CartController',
        	controllerAs: 'CartCtrl'	
        
    })
    
     .when('/Orders', {
        templateUrl : 'partial/ViewOrders.html',
        controller  : 'ViewOrderController'
        
    })
    
    .when('/AdminHome', {
        templateUrl : 'partial/AdminHome.html',
        controller  : 'adminhomeController'
        
    })
    
     .when('/admin/sellerReg', {
        templateUrl : 'partial/sellerReg.html',
        controller  : 'sellerRegController',
        controllerAs: 'sellerCtrl'
        
    })
    
        .when('/admin/viewCustomers', {
        templateUrl : 'partial/viewCustomers.html',
        controller  : 'viewCustController',
        controllerAs: 'viewCustCtrl'
        
    })
    
     .when('/seller/productReg', {
        templateUrl : 'partial/productReg.html',
        controller  : 'productRegController',
        controllerAs: 'productRegCtrl'
        
    })
    
      .when('/SellerHome', {
        templateUrl : 'partial/SellerHome.html',
        controller  : 'sellerhomeController',
        controllerAs: 'sellerhomeCtrl'
        
    })
    
    .when('/seller/viewProducts', {
        templateUrl : 'partial/ViewSellerProducts.html',
        controller  : 'viewSellerProductController',
        controllerAs: 'viewSellerProdCntrl'
        
    })
    
       .when('/ViewTopProduct', {
        templateUrl : 'partial/topProduct.html',
        controller  : 'topProductController',
        controllerAs: 'topProdCntrl'
        
    })
    
    .otherwise({ redirectTo: '/' });
})


.run(
    function ($rootScope, $location, $cookieStore,$window, $http,AUTH_EVENTS,APP_CONSTANT) {
    	//Management 
    	$rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
    		console.log('Clicked on '+$location.path());
            if ( !($location.path() == '/'
            		|| $location.path() == '/registration'
            		|| $location.path() == '/login')
            		  
            		  && !$rootScope.globals.userSession) {
            		console.log('Invalid Path')
                $location.path('/');
            }else if($location.path() == '/logout'){
            		$rootScope.$broadcast(AUTH_EVENTS.logoutSuccess);
            }else{
            
        		console.log('Valid Path')

            }
        });
    	
	$rootScope.$on(AUTH_EVENTS.loginFailed, function(event, next){
    		console.log('Login failed');
        

    		//$scope.message = "Login failed";
    });
	
	$rootScope.$on(AUTH_EVENTS.logoutSuccess, function(event, next){
		console.log('Logout Success');
		$window.localStorage.removeItem("globals");
		$rootScope.userSession=null;
	    $rootScope.$emit("CallParentMethod", {});

		//$scope.message = "Login failed";
});
 	
    $rootScope.$on(AUTH_EVENTS.loginSuccess, function(event, next){
		//$scope.message = "Login Success";
		console.log('Login success');
	    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
	    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;
	    
	    $rootScope.$emit("CallParentMethod", {});
	    if($rootScope.globals.userSession.role=='customer'){
		$location.path('/dashboard');}
	    
	    else if($rootScope.globals.userSession.role=='admin'){
	    	$location.path('/AdminHome');
	    }
	    else if($rootScope.globals.userSession.role=='seller'){
	    	$location.path('/SellerHome');
	    }
	    
    });
    
    // keep user logged in after page refresh
    $rootScope.globals = $cookieStore.get('globals') || {};
    if ($rootScope.globals.userSession) {
        $http.defaults.headers.common[APP_CONSTANT.AUTH_KEY] = $rootScope.globals.userSession.authKey; // jshint ignore:line
	    $window.localStorage.setItem("globals", angular.toJson($rootScope.globals));
	    $rootScope.userSession = JSON.parse($window.localStorage.getItem("globals")).userSession;

    }

    

})



