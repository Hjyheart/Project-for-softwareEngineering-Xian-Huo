/**
 * Created by hongjiayong on 2016/12/24.
 */
app.controller('club-homeCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.isLogin = false;

    this.$onInit = function () {
        $http({
            method: 'POST',
            url: constService.urls().loginIf
        }).then( res=>{
            if (res.data !== '') {
                $scope.isLogin = true;
                $scope.student = res.data;
            }
        }).catch( err=>{
            console.log(err);
        });


    }
}]);