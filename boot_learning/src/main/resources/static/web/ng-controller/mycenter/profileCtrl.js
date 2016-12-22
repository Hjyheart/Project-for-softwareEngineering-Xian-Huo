/**
 * Created by hongjiayong on 2016/12/10.
 */
app.controller('profileCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.student;
    $scope.clubs;
    $scope.hclubs;
    $scope.isLogin = false;
    this.$onInit = function(){
        // 判断是否登录并获取个人基本信息
        $http({
            method: 'POST',
            url: constService.urls().loginIf
        }).then( res=>{
            console.log(res.data);
            if (res.data !== ''){
                $scope.isLogin = true;
                $scope.student = res.data;
                $scope.user = $scope.student.mName;
                $scope.major = $scope.student.mMajor;
                $scope.contact = $scope.student.mContact;
                $scope.grade = $scope.student.mGrade;

                // 获取该学生的俱乐部信息
                $http({
                    method: 'POST',
                    url: constService.urls().myclubs,
                    params:{
                        'id': $scope.student.mId
                    }
                }).then( res=>{
                    console.log(res.data);
                    $scope.clubs = res.data;
                }).catch( err=>{
                    console.log(err);
                });

                // 获取该学生创建的俱乐部的信息
                $http({
                    method: 'POST',
                    url: constService.urls().myHostClub,
                    params:{
                        'id': $scope.student.mId
                    }
                }).then( res=>{
                    console.log(res.data);
                    $scope.hclubs = res.data.host_clubs;
                }).catch( err=>{
                    console.log(err);
                })
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

    $scope.more = function (club) {
        window.location.href = '/club/' + club.mId;
    };

    $scope.manage = function (club) {
        window.location.href = '/club/' + club.mId + '/manage';
    };

    $scope.delete = function (club) {
        // 把一个club从一个user中移除
        $http({
            method: 'POST',
            url: constService.urls().deleteStudent,
            params:{
                's_id': $scope.student.mId,
                'c_id': club.mId
            }
        }).then( res=>{
            console.log(res.data);
            for (let i = 0; i < $scope.clubs.length; i++){
                if ($scope.clubs[i] === club){
                    $scope.clubs.splice(i ,1);
                    break;
                }
            }
        }).catch( err=>{
            console.log(err);
        })
    };
}]);