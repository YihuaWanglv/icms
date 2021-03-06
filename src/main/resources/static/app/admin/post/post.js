(function(angular) {
	angular.module("post.controllers", []);
	angular.module("post.services", []);
	var app = angular.module("post", ["ngResource", "ngSanitize", "post.controllers", "post.services"], function($locationProvider) {
		$locationProvider.html5Mode(true);
	});
	app.directive('contenteditable', function() {
        return {
            restrict: 'A' ,
            require: '?ngModel',
            link: function(scope, element, attrs, ngModel) {
                // 初始化 编辑器内容
                if (!ngModel) {
                    return;
                } // do nothing if no ng-model
                // Specify how UI should be updated
                ngModel.$render = function() {
                    element.html(ngModel.$viewValue || '');
                };
                // Listen for change events to enable binding
                element.on('blur keyup change', function() {
                    scope.$apply(readViewText);
                });
                // No need to initialize, AngularJS will initialize the text based on ng-model attribute
                // Write data to the model
                function readViewText() {
                    var html = element.html();
                    // When we clear the content editable the browser leaves a <br> behind
                    // If strip-br attribute is provided then we strip this out
                    if (attrs.stripBr && html === '<br>') {
                        html = '';
                    }
                    ngModel.$setViewValue(html);
                }
    
                // 创建编辑器
                var editor = new wangEditor(element);
                // 上传图片（举例）
    			// editor.config.uploadImgUrl = '/upload/image';
    			editor.config.uploadImgUrl = '/upload/uploadSingleImage';
                editor.create();
            }
        };
    });
	// app.directive('contenteditable', function() {
 //        return {
 //            restrict: 'A' ,
 //            require: 'ngModel',
 //            link: function(scope, element, attrs, ctrl) {
 //                // 创建编辑器
 //                var editor = new wangEditor('editor-trigger');
 //                editor.onchange = function () {
 //                    // 从 onchange 函数中更新数据
 //                    scope.$apply(function () {
 //                        var html = editor.$txt.html();
 //                        ctrl.$setViewValue(html);
 //                    });
 //                };
 //                editor.create();
 //            }
 //        };
 //    });
}(angular));