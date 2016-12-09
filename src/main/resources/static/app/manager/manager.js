(function(angular) {
  angular.module("manager.controllers", []);
  angular.module("manager.services", []);
  angular.module("manager", ["ngResource", "manager.controllers", "manager.services"]
  	, function($locationProvider) {
    	$locationProvider.html5Mode(true);
    }
  );
}(angular));