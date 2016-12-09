/**
 * Created by hongjiayong on 2016/12/9.
 */
app.controller('loginCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    this.$onInit = function (){
        $http({
            method: 'GET',
            url: constService.urls().loginIf
        }).then(res =>{

        }).catch(err =>{
            console.log(err);
        });
    };
}]);