/**
 * Created by hongjiayong on 2016/12/10.
 */
app.controller('activity-homeCtrl', ['$scope', '$http', 'constService', function($scope, $http, constService){
    $scope.isLogin = false;
    $scope.student;
    $scope.activities;
    this.$onInit = function () {
        $http({
            method: 'POST',
            url: constService.urls().loginIf
        }).then( res=>{
            if (res.data !== ''){
                $scope.isLogin = true;
                $scope.student = res.data;
            }
        }).catch( err=>{
            console.log(err);
        });

        // 获取活动列表
        $http({
            method: 'POST',
            url: constService.urls().getAllActivities
        }).then( res=>{
            console.log(res.data);
            $scope.activities = res.data;
        }).catch( err=>{
            console.log(err);
        })
    };
}]);