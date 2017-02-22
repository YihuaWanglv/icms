(function(angular) {
  var ConfigHomeController = function($scope, $http, TemplateHome) {
    $scope.templateHome = {};
    $scope.isOnWebSiteHeader = true;
    $scope.isOnHeadline = true;
    $scope.headlineType=0;
    $scope.headlineCategoryId=0;
    $scope.headlineCategory;
    $scope.templates = [{
      templateId:1,
      defaultCount:6,
      templateName:'2列简朴模板'
    },{
      templateId:2,
      defaultCount:6,
      templateName:'3列蓝色模板'
    }]
    $http.get('/admin/config/home')
      .success(function(data, status, headers, config){
        console.log(data);
        $scope.templateHome = (data)?data:[];
        $scope.isOnWebSiteHeader = $scope.templateHome.isOnWebSiteHeader?true:false;
        $scope.isOnHeadline = $scope.templateHome.isOnHeadline?true:false;
        $scope.headlineType = $scope.templateHome.headlinePostId>0?0:1;
        $scope.headlineCategoryId = $scope.templateHome.headlineCategoryId;
        for (var i = 0; i < $scope.templateHome.templateItems.length; i++) {
          $scope.templateHome.templateItems[i].temp=findSelectedTemplate($scope.templateHome.templateItems[i].templateId);
        }
        console.log($scope.templateHome);
        $http.get('/admin/category/list',{params: {pid:0}})
          .success(function(data, status, headers, config){
            console.log(data);
            $scope.categorys = (data)?data:[];
            $scope.headlineCategory = findSelected($scope.headlineCategoryId);
            for (var i = 0; i < $scope.templateHome.templateItems.length; i++) {
              $scope.templateHome.templateItems[i].cate=findSelected($scope.templateHome.templateItems[i].categoryId);
            }
          }).error(function(data, status, headers, config){
            $scope.categorys = [];
            alert('加载类目列表失败.');
          }
        );
      })
      .error(function(data, status, headers, config){
        alert('load failed');
      }
    );
    console.log($scope.templateHome);
    $scope.save = function(templateHome) {
      TemplateHome.$save(function(templateHome) {
        alert('保存成功');
      });
    };
    $scope.addStaticPost = function(item) {
      item.staticPosts.push(0);
    }
    $scope.addTemplateItem = function(templateHome) {
      templateHome.templateItems.push(createNewTemplateItem());
    }
    function createNewTemplateItem() {
      return {
        cate: {},
        categoryId: 0,
        count: 6,
        indexs: 0,
        name: '',
        staticPosts: [],
        temp: {},
        templateId: 1,
        tiid: null
      };
    }
    function findSelected(_id) {
      for (var i = 0; i < $scope.categorys.length; i++) {
        if ($scope.categorys[i].cid == _id) return $scope.categorys[i];
      }
      return null;
    }
    function findSelectedTemplate(_id) {
      for (var i = 0; i < $scope.templates.length; i++) {
        if ($scope.templates[i].templateId == _id) return $scope.templates[i];
      }
      return null;
    }
  };
  ConfigHomeController.$inject = ['$scope', '$http', 'TemplateHome'];
  angular.module('config-home.controllers').controller('ConfigHomeController', ConfigHomeController);
}(angular));