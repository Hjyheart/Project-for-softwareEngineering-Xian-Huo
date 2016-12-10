/**
 * Created by hongjiayong on 2016/12/9.
 */
app.controller('loginCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.isLogin;
    $scope.passwordError = false;
    $scope.schoolName = '软件学院';
    $scope.user;
    $scope.contact;
    $scope.major;
    $scope.grade = "2014";
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
                    $('.ui.modal').modal({
                        closable: false,
                        blurring: true,
                        transition: 'horizontal flip',
                        onApprove : function() {
                            $http({
                                method: 'POST',
                                url: constService.urls().compelete,
                                params:{
                                    'id': stuId,
                                    'user': $scope.user,
                                    'major': $scope.major,
                                    'grade': $('#gradeValue').val(),
                                    'contact': $scope.contact
                                }
                            }).then( res=>{
                                window.location.href = '/home';
                            }).catch(err =>{
                                $('.ui.modal').modal('show');
                            })

                        }
                    }).modal('show');
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