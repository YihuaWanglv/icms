(function(angular) {

	angular.module('admin.services').factory('Item', function($resource) {
		return $resource('/admin/item/:iid', {

		}, {
			update : {
				method : "PUT"
			},
			remove : {
				method : "DELETE"
			}
		});
	});

	angular.module('admin.services').factory('Point', function($resource) {
		return $resource('/admin/point/:iid', {
			iid : '@iid'
		}, {
			update : {
				method : "PUT"
			},
			remove : {
				method : "DELETE"
			}
		});
	});

	// angular.module('manager.services').factory('UserPoint', function($resource) {
	// 	return $resource('/point/:iid', {
	// 		iid : '@iid'
	// 	}, {
	// 		update : {
	// 			method : "PUT"
	// 		},
	// 		remove : {
	// 			method : "DELETE"
	// 		}
	// 	});
	// });

}(angular));