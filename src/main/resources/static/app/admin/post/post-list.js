(function(angular) {
	angular.module("post.controllers", []);
	angular.module("post.services", []);
	angular.module("post", ["ngResource", "post.controllers", "post.services"], function($locationProvider) {
		$locationProvider.html5Mode(true);
	});
}(angular));