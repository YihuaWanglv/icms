(function(angular) {
	angular.module('category.services').factory('Category', function($resource) {
		return $resource('/admin/category/:cid', {
			cid:'@cid'
		}, {
			update : {
				method : "PUT"
			},
			remove : {
				method : "DELETE"
			}
		});
	});
}(angular));