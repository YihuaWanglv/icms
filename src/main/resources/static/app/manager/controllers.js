(function(angular) {
  var ItemController = function($scope, Item) {
    Item.query(function(response) {
      $scope.items = response ? response : [];
    });
    $scope.addItem = function(name) {
      new Item({
        name: name
      }).$save(function(item) {
        $scope.items.push(item);
      });
      $scope.newItem = "";
    };
    $scope.updateItem = function(item) {
      item.$update();
    };
    $scope.deleteItem = function(item) {
      item.$remove(function() {
        $scope.items.splice($scope.items.indexOf(item), 1);
      });
    };
    $scope.edit = true;
    $scope.changeButtonFlag = function() {
      $scope.edit = (!$scope.edit);
    }
  };

  var PointController = function($scope, Point, $location) {
    $scope.location = $location;
    $scope.iid = ($location.search()).iid;

    Point.query({iid: $scope.iid}, function(response) {
      $scope.points = response ? response : [];
    });
    $scope.addPoint = function(name) {
      new Point({
        name: name
      }).$save(function(point) {
        $scope.points.push(point);
      });
      $scope.newPoint = "";
    };
    $scope.updatePoint = function(point) {
      console.log(point);
      point.$update(function(point) {
        console.log('newPoint');
        console.log(point);
        // $scope.points.push(point);
      });
    };
    $scope.deletePoint = function(point) {
      point.$remove(function() {
        $scope.points.splice($scope.points.indexOf(point), 1);
      });
    };
    // $scope.edit = true;
    $scope.changeButtonFlag = function(point) {
      // $scope.edit = (!$scope.edit);
      point.editting = !point.editting;
    }
  };
  
  ItemController.$inject = ['$scope', 'Item'];
  PointController.$inject = ['$scope', 'Point', '$location'];

  angular.module("manager.controllers").controller("ItemController", ItemController);
  angular.module("manager.controllers").controller("PointController", PointController);
}(angular));