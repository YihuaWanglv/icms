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
    $scope.changeButtonFlag = function() {
      $scope.editting = !$scope.editting;
    }
  };


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
      // if (!$scope.post.cid) {
      //   alert('请选择类目');
      // }
      if ($scope.command > 0 && $scope.pid && $scope.pid > 0) {
        post.$update(function(post) {
          layer.msg('保存成功');
          window.location.reload();
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
        });
      }
    };
    $scope.deletePost = function(post) {
      post.$remove(function() {
        $scope.posts.splice($scope.posts.sdexOf(post), 1);
      });
    };
    
  };

  
  
  CategoryController.$inject = ['$scope', '$http', 'Category'];
  PostListController.$inject = ['$scope', '$http', 'Post', '$location'];
  PostController.$inject = ['$scope', '$http', 'Post', '$location'];

  angular.module('admin.controllers').controller('CategoryController', CategoryController);
  angular.module('admin.controllers').controller('PostListController', PostListController);
  angular.module('admin.controllers', ['ng.ueditor']).controller('PostController', PostController);
}(angular));