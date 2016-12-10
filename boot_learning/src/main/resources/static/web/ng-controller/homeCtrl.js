/**
 * Created by hongjiayong on 2016/12/8.
 */
app.controller('homeCtrl', ['$scope', '$http', 'constService', function($scope, $http, constService){
    $scope.isLogin = false;
    this.$onInit = function(){
        $http({
            method: 'POST',
            url: constService.urls().loginIf
        }).then( res=>{
            if (res.data === true){
                $scope.isLogin = true;
            }
        }).catch( err=>{
           console.log(err);
        });
    };
}]);