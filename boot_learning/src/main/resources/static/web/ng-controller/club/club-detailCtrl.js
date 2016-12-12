/**
 * Created by hongjiayong on 2016/12/12.
 */
app.controller('club-detailCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.isLogin = false;
    $scope.isApply = false;
    $scope.student;
    $scope.club;
    $scope.chairman;
    this.$onInit = function(){
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

        $http({
            method: 'POST',
            url: constService.urls().clubdetail,
            params:{
                'id': $('#clubId').text()
            }
        }).then( res =>{
            console.log(res);
            $scope.club = res.data;
            $http({
                method: "POST",
                url: constService.urls().getstudent,
                params:{
                    'id': $scope.club.mChairmanId
                }
            }).then( res=>{
                $scope.chairman = res.data.mName;
            }).catch( err=>{
                console.log(err);
            });
            if ($scope.isLogin === true){
                // 得到申请状态
                $http({
                    method: "POST",
                    url: constService.urls().getState,
                    params:{
                        's_id': $scope.student.id,
                        'c_id': $scope.club.mId
                    }
                }).then( res=>{
                    $scope.isApply = res.data;
                }).catch( err=>{
                    console.log(err);
                })
            }
        }).catch( err =>{
            console.log(err);
        });
    };

    $scope.sendApply = function(){
        if ($scope.isLogin) {
            $http({
                method: 'POST',
                url: constService.urls().addStudent,
                params: {
                    's_id': $scope.student.id,
                    'c_id': $scope.club.mId
                }
            }).then(res=> {
                $scope.isApply = true;
            }).catch(err=> {
                alert("申请失败");
                console.log(err);
            })
        }
    };

    $scope.sendQuit = function() {
        if ($scope.isLogin) {
            $http({
                method: 'POST',
                url: constService.urls().deleteStudent,
                params: {
                    's_id': $scope.student.id,
                    'c_id': $scope.club.mId
                }
            }).then(res=> {
                $scope.isApply = false;
            }).catch(err=> {
                alert("申请失败");
                console.log(err);
            });
        }
    };
}]);