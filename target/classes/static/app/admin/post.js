(function(angular) {
  angular.module('controllers.ckeditor', ['ckeditor'])
	.controller('CkeditorCtrl', ['$scope', function ($scope) {

	  // Editor options.
	  $scope.options = {
	    language: 'en',
	    allowedContent: true,
	    entities: false
	  };

	  // Called when the editor is completely ready.
	  $scope.onReady = function () {
	    // ...
	  };
	}]);
	angular.module('appPost', ['ngResource', 'controllers.ckeditor']);
}(angular));