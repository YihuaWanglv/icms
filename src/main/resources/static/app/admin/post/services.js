(function(angular) {
	angular.module('post.services').factory('Post', function($resource) {
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