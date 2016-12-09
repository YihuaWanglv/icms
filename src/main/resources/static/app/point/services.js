(function(angular) {

  angular.module('point.services').factory('UserPoint', function($resource) {
    return $resource('/user/point/:pid', {
      pid: '@pid'
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  });
  
}(angular));