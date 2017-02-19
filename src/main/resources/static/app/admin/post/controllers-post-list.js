(function(angular) {
  
  var PostListController = function($scope, $http, Post, $location) {
    $scope.location = $location;
    $scope.cid = ($location.search()).cid;
    $scope.newScores = 1;
    $http.get('/admin/post/list',{params: {cid:$scope.cid}})
      .success(function(data, status, headers, config){
        console.log(data);
        $scope.posts = (data)?data:[];
      })
      .error(function(data, status, headers, config){
        $scope.posts = [];
        alert('load failed');
      }
    );
    $scope.addPost = function(name, iid) {
      new Post({
        title: $scope.newTitle,
        description: $scope.newDescription,
        scores: $scope.newScores,
        iid: $scope.iid
      }).$save(function(post) {
        $scope.posts.push(post);
      });
      $scope.newTitle = "";
      $scope.newDescription = "";
      $scope.newScores = 1;
    };
    $scope.updatePost = function(post) {
      console.log(post);
      post.$update(function(post) {
        console.log(post);
      });
    };
    $scope.deletePost = function(post) {
      post.$remove(function() {
        $scope.posts.splice($scope.posts.sdexOf(post), 1);
      });
    };
    $scope.changeButtonFlag = function(post) {
      post.editting = !post.editting;
    }
  };
  PostListController.$inject = ['$scope', '$http', 'Post', '$location'];
  angular.module('post.controllers').controller('PostListController', PostListController);
}(angular));