(function(angular) {

	angular.module('admin.services').factory('Category', function($resource) {
		return $resource('/admin/category/:iid', {

		}, {
			update : {
				method : "PUT"
			},
			remove : {
				method : "DELETE"
			}
		});
	});

	angular.module('admin.services').factory('Post', function($resource) {
		return $resource('/admin/post/:pid', {
			pid : '@pid'
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