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
                $scope.user = $scope.student.name;
                $scope.major = $scope.student.major;
                $scope.contact = $scope.student.contact;
            }else{
                window.location.href = '/login';
            }
        }).catch( err=>{
            console.log(err);
        });
    };

    $scope.showEdit = function () {
        $('.ui.modal').modal({
            closable: false,
            blurring: true,
            transition: 'horizontal flip',
            onApprove : function() {
                $http({
                    method: 'POST',
                    url: constService.urls().compelete,
                    params:{
                        'id': $scope.student.id,
                        'user': $scope.user,
                        'major': $scope.major,
                        'grade': $('#gradeValue').val(),
                        'contact': $scope.contact
                    }
                }).then( res=>{
                    console.log('success');
                    $scope.student.name = $scope.user;
                    $scope.student.contact = $scope.contact;
                    $scope.student.grade = $('#gradeValue').val();
                }).catch(err =>{
                    alert('网络问题!');
                })

            }
        }).modal('show');
    };
}]);