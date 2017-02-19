(function(angular) {
  angular.module("admin.controllers", []);
  angular.module("admin.services", []);
  angular.module("admin", ["ngResource", "ngSanitize"
  	// , 'controllers.ckeditor'
  	, "admin.controllers", "admin.services"]
  	, function($locationProvider) {
    	$locationProvider.html5Mode(true);
    }
  );
}(angular));