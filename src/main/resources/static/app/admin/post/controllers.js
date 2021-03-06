(function(angular) {
  var PostController = function($scope, $http, Post, $location, $sce) {
    $scope.location = $location;
    $scope.pid = ($location.search()).pid;
    $scope.cid = ($location.search()).cid;
    $scope.command = ($location.search()).command;
    console.log($scope.command);
    $scope.editting = ($scope.command==0 || $scope.command==2) ? 1 : 0;
    $scope.post = {title:'',content:''};
    console.log($scope.editting);
    console.log($scope.post);
    console.log($scope.cid);
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
            $scope.changeButtonFlag();
            // window.location.href='/view/admin/post.html?command=1&pid='+$scope.pid+'&cid='+$scope.cid;
          } else {
            $scope.changeButtonFlag();
            // window.location.reload();
          }
        });
      } else {
        if (!$scope.cid) {
          layer.msg('请选择类目');
          return;
        }
        new Post({
          title: $scope.post.title,
          introduction: $scope.post.introduction,
          content: $scope.post.content,
          cid: $scope.cid
        }).$save(function(post) {
          $scope.pid=post.pid;
          $scope.post = post;
          layer.msg('保存成功');
          window.location.href='/view/admin/post.html?command=1&pid='+$scope.pid+'&cid='+$scope.cid;
        });
      }
    };
    $scope.deletePost = function(post) {
      post.$remove(function() {
        $scope.posts.splice($scope.posts.sdexOf(post), 1);
      });
    };
    $scope.renderHtml = function (htmlCode) {
        return $sce.trustAsHtml(htmlCode);
    };
    
  };

  PostController.$inject = ['$scope', '$http', 'Post', '$location', '$sce'];
  // angular.module('post.controllers', ['ng.ueditor']).controller('PostController', PostController);
  angular.module('post.controllers').controller('PostController', PostController);
}(angular));