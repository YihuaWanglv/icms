(function(angular) {
  angular.module("config-home.controllers", []);
  angular.module("config-home.services", []);
  angular.module("config-home", ["ngResource", 'ngSanitize', 'ui.select', "config-home.controllers", "config-home.services"]
  	, function($locationProvider) {
    	$locationProvider.html5Mode(true);
    }
  );
}(angular));