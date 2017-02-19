(function(angular) {
  angular.module("category.controllers", []);
  angular.module("category.services", []);
  angular.module("category", ["ngResource", "category.controllers", "category.services"]
  	, function($locationProvider) {
    	$locationProvider.html5Mode(true);
    }
  );
}(angular));