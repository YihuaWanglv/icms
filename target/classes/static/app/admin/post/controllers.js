(function(angular) {
  var PostController = function($scope, $http, Post, $location) {
    $scope.location = $location;
    $scope.pid = ($location.search()).pid;
    $scope.cid = ($location.search()).cid;
    $scope.command = ($location.search()).command;
    console.log($scope.command);
    $scope.editting = ($scope.command==0 || $scope.command==2) ? 1 : 0;
    $scope.post = {title:'',content:''};
    console.log($scope.editting);
    console.log($scope.post);
    if ($scope.command > 0) {
      Post.get({pid: $scope.pid}, function(response) {
        console.log(response);
        $scope.post = response ? response : {};
      });
    }
    $scope.changeButtonFlag = function() {
      $scope.editting = !$scope.editting;
    };
    $scope.save = function(post) {
      if ($scope.command > 0 && $scope.pid && $scope.pid > 0) {
        post.$update(function(post) {
          layer.msg('保存成功');
          if ($scope.command=2) {
            window.location.href='/view/admin/post.html?command=1&pid='+$scope.pid;
          } else {
            window.location.reload();
          }
        });
      } else {
        if (!$scope.cid) {
          layer.msg('请选择类目');
          return;
        }
        new Post({
          title: $scope.post.title,
          content: $scope.post.content,
          cid: $scope.cid
        }).$save(function(post) {
          $scope.pid=post.pid;
          $scope.post = post;
          layer.msg('保存成功');
          window.location.href='/view/admin/post.html?command=1&pid='+$scope.pid;
        });
      }
    };
    $scope.deletePost = function(post) {
      post.$remove(function() {
        $scope.posts.splice($scope.posts.sdexOf(post), 1);
      });
    };
    
  };

  PostController.$inject = ['$scope', '$http', 'Post', '$location'];
  angular.module('post.controllers', ['ng.ueditor']).controller('PostController', PostController);
}(angular));