/**
 * Created by hongjiayong on 2016/12/25.
 */
app.controller('adminCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService){
    $scope.isLogin = false;
    $scope.student;
    $scope.clubs;

    this.$onInit = function(){
        $http({
            method: 'POST',
            url: constService.urls().loginIf
        }).then( res=> {
            console.log(res.data);
            if (res.data !== '') {
                $scope.isLogin = true;
                $scope.student = res.data;

                // 获取俱乐部创建申请
                $http({
                    method: 'POST',
                    url: constService.urls().getAllClub
                }).then( res=>{
                    console.log(res.data);
                    $scope.clubs = res.data;
                }).catch( err=>{
                    console.log(err);
                });

                // 获取场地申请


                // 获取海报张贴申请

            }else{
                window.location.href = '/login'
            }
        }).catch( err=>{
            console.log(err);
        });
    };

    // 拒绝请求并删除该社团
    $scope.rejected = function (club) {
        $http({
            method: 'POST',
            url: constService.urls().rejectClub,
            params: {
                'id': club.mId
            }
        }).then( res=>{
            // 获取俱乐部创建申请
            $http({
                method: 'POST',
                url: constService.urls().getAllClub
            }).then( res=>{
                console.log(res.data);
                $scope.clubs = res.data;
            }).catch( err=>{
                console.log(err);
            });
        }).catch( err=>{
            console.log(err);
        })
    };

    // 同意申请
    $scope.approve = function (club) {
        $http({
            method: 'POST',
            url: constService.urls().acceptClub,
            params: {
                'id': club.mId
            }
        }).then( res=>{
            // 获取俱乐部创建申请
            $http({
                method: 'POST',
                url: constService.urls().getAllClub
            }).then( res=>{
                console.log(res.data);
                $scope.clubs = res.data;
            }).catch( err=>{
                console.log(err);
            });
        }).catch( err=>{
            console.log(err);
        })
    };
}]);