(function(angular) {

  // var CategoryFactory = function($resource) {
  //   return $resource('/manager/category/:categoryId', {
  //     categoryId: '@categoryId'
  //   }, {
  //     update: {
  //       method: "PUT"
  //     },
  //     remove: {
  //       method: "DELETE"
  //     }
  //   });
  // };
  // CategoryFactory.$inject = ['$resource'];
  // angular.module("manager.services").factory("Category", CategoryFactory);


  angular.module('manager.services').factory('Item', function($resource) {
    return $resource('/item/list/wait', {
      
    }, {
      update: {
        method: "PUT"
      },
      remove: {
        method: "DELETE"
      }
    });
  });

  angular.module('manager.services').factory('Point', function($resource) {
    return $resource('/point/:iid', {
      iid: '@iid'
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