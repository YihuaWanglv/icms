(function(angular) {
 	angular.module('user-app',['ngResource']);
 	angular.module('user-app.services').factory('User', function($resource) {
	  return $resource('/user/:uid');
	});
	angular.module('user-app.controllers',[]);

	angular.module('user-app.controllers').controller('UserController',function($scope, $location, User) {
		$scope.location = $location;
	    $scope.$watch('location.search()', function() {
	        $scope.uid = ($location.search()).uid;
	    }, true);
	    console.log($scope.uid);
	    // $scope.changeTarget = function(name) {
	    //     $location.search('target', name);
	    // }
		$scope.user = User.get({ uid: $scope.uid }, function() {
			console.log('get user ok.');
		});
		console.log($scope.user);
		// $scope.user = new User();
		// $scope.user.data = 'some data';
		// User.save($scope.user, function() {});
	});
}(angular));