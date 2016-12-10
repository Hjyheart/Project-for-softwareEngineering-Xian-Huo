/**
 * Created by hongjiayong on 2016/12/9.
 */
app.controller('loginCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.passwordError = false;
    this.$onInit = function (){

    };

    $scope.login = function (stuId, pwd){
        console.log(stuId);
        console.log(pwd);
        if (stuId === undefined || stuId === ''){
            $('#stuId').popup('show');
            return;
        }
        if (pwd === undefined || pwd === ''){
            $('#password').popup('show');
            return;
        }
        $http({
            method: 'POST',
            url: constService.urls().vertify,
            params: {
                id: stuId,
                password: pwd
            }
        }).then(res =>{
            console.log(res.data);
            switch (res.data){
                case 1:
                    window.location.href = '/home';
                    break;
                case 0:
                    window.location.href = '/init';
                    break;
                case -1:
                    $scope.passwordError = true;
                    break;
            }
        }).catch(err =>{
            console.log(err);
        })
    };

    $scope.removeError = function (){
        $scope.passwordError = false;
        $('#stuId').popup('hide');
        $('#password').popup('hide');
    }


}]);