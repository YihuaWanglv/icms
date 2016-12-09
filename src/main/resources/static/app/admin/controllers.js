(function(angular) {
  var ItemController = function($scope, $http, Item) {
    // Item.query(function(response) {
    //   $scope.items = response ? response : [];
    // });
    $scope.piid = 0;
    
    $http.get('/admin/item',{params: {piid:$scope.piid}})
      .success(function(data, status, headers, config){
        console.log(data);
        $scope.items = (data)?data:[];
        // $scope.items = response ? response : [];
      })
      .error(function(data, status, headers, config){
        $scope.items = [];
        alert('load failed');
      });
    $scope.listNext = function(item) {

      $scope.piid = item.iid;
      listItem();
    }
    var listItem = function() {
      $http.get('/admin/item',{params: {piid:$scope.piid}})
      .success(function(data, status, headers, config){
        $scope.items = (data)?data:[];
        // $scope.items = response ? response : [];
      })
      .error(function(data, status, headers, config){
        $scope.items = [];
        alert('load failed');
      });
    }
    $scope.addItem = function(name, piid) {
      new Item({
        name: name,
        piid: piid
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
    // $scope.edit = true;
    $scope.changeButtonFlag = function() {
      $scope.editting = !$scope.editting;
    }
  };


  var PointController = function($scope, Point, $location) {
    $scope.location = $location;
    $scope.iid = ($location.search()).iid;
    $scope.newScores = 1;
    Point.query({iid: $scope.iid}, function(response) {
      $scope.points = response ? response : [];
    });
    $scope.addPoint = function(name, iid) {
      new Point({
        title: $scope.newTitle,
        description: $scope.newDescription,
        scores: $scope.newScores,
        iid: $scope.iid
      }).$save(function(point) {
        $scope.points.push(point);
      });
      $scope.newTitle = "";
      $scope.newDescription = "";
      $scope.newScores = 1;
    };
    $scope.updatePoint = function(point) {
      console.log(point);
      point.$update(function(point) {
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

  
  
  ItemController.$inject = ['$scope', '$http', 'Item'];
  PointController.$inject = ['$scope', 'Point', '$location'];

  angular.module("admin.controllers").controller("ItemController", ItemController);
  angular.module("admin.controllers").controller("PointController", PointController);
}(angular));