/**
 * Created by hongjiayong on 2016/12/10.
 */
app.controller('profileCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.student;
    $scope.isLogin = false;
    this.$onInit = function(){
        $http({
            method: 'POST',
            url: constService.urls().loginIf
        }).then( res=>{
            console.log(res.data);
            if (res.data !== ''){
                $scope.isLogin = true;
                $scope.student = res.data;
                //$http({
                //    method: 'POST',
                //    url:
                //})
            }else{
                window.location.href = '/login';
            }
        }).catch( err=>{
            console.log(err);
        });
    }
}]);