(function(angular) {
	angular.module('config-home.services').factory('TemplateHome', function($resource) {
		return $resource('/admin/config/home/:cid');
	});
}(angular));