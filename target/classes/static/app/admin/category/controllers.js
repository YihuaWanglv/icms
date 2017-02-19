(function(angular) {
  var CategoryController = function($scope, $http, Category) {
    $scope.pid = 0;
    $scope.newCategory = "";
    $http.get('/admin/category/list',{params: {pid:$scope.pid}})
      .success(function(data, status, headers, config){
        console.log(data);
        $scope.categorys = (data)?data:[];
      })
      .error(function(data, status, headers, config){
        $scope.categorys = [];
        alert('load failed');
      });
    $scope.listNext = function(category) {
      $scope.pid = category.cid;
      listCategory();
    }
    var listCategory = function() {
      $http.get('/admin/category/list',{params: {pid:$scope.pid}})
      .success(function(data, status, headers, config){
        $scope.categorys = (data)?data:[];
      })
      .error(function(data, status, headers, config){
        $scope.categorys = [];
        alert('load failed');
      });
    }
    $scope.addCategory = function(name) {
      console.log(name);
      new Category({
        name: name
      }).$save(function(category) {
        $scope.categorys.push(category);
      });
      $scope.newCategory = "";
    };
    $scope.updateCategory = function(category) {
      category.$update();
    };
    $scope.deleteCategory = function(category) {
      category.$remove(function() {
        $scope.categorys.splice($scope.categorys.indexOf(category), 1);
      });
    };
    // $scope.edit = true;
    $scope.changeButtonFlag = function(category) {
      console.log('changeButtonFlag');
      category.editting = !category.editting;
    }
  };
  CategoryController.$inject = ['$scope', '$http', 'Category'];
  angular.module('category.controllers').controller('CategoryController', CategoryController);
}(angular));