(function(angular) {
  angular.module("point.controllers", ['ckeditor']);
  angular.module("point.services", []);
  angular.module("point", ["ngResource", "point.controllers", "point.services"]
  	, function($locationProvider) {
    	$locationProvider.html5Mode(true);
    }
  );
}(angular));