/**
 * Created by hongjiayong on 2016/12/25.
 */
app.controller('adminCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService){
    $scope.isLogin = false;
    $scope.student;
    $scope.clubs;
    $scope.applies;

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

                // 获得请求列表
                $http({
                    method: 'POST',
                    url: constService.urls().getAllApplies,
                }).then( res=>{
                    console.log(res.data);
                    $scope.applies = res.data;
                    for (let i = 0; i < $scope.applies.length; i++){
                        let startdate = new Date($scope.applies[i].mStartDate);
                        $scope.applies[i].mStartDate = startdate.getFullYear().toString() + '/'
                            + startdate.getMonth().toString() + '/'
                            + startdate.getDay().toString() + '  '
                            + startdate.getHours().toString() + ':'
                            + startdate.getMinutes().toString();
                        let enddate = new Date($scope.applies[i].mEndDate);
                        $scope.applies[i].mEndDate = enddate.getFullYear().toString() + '/'
                            + enddate.getMonth().toString() + '/'
                            + enddate.getDay().toString() + '  '
                            + enddate.getHours().toString() + ':'
                            + enddate.getMinutes().toString();
                    }
                }).catch( err=>{
                    console.log(err);
                });

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

    // 同意社团申请
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

    // 同意请求
    $scope.approveApply = function (apply) {
        $http({
            method: 'POST',
            url: constService.urls().approveApply,
            params:{
                'id': apply.mId
            }
        }).then( res=>{
            // 获得请求列表
            $http({
                method: 'POST',
                url: constService.urls().getAllApplies,
            }).then( res=>{
                console.log(res.data);
                $scope.applies = res.data;
                for (let i = 0; i < $scope.applies.length; i++){
                    let startdate = new Date($scope.applies[i].mStartDate);
                    $scope.applies[i].mStartDate = startdate.getFullYear().toString() + '/'
                        + startdate.getMonth().toString() + '/'
                        + startdate.getDay().toString() + '  '
                        + startdate.getHours().toString() + ':'
                        + startdate.getMinutes().toString();
                    let enddate = new Date($scope.applies[i].mEndDate);
                    $scope.applies[i].mEndDate = enddate.getFullYear().toString() + '/'
                        + enddate.getMonth().toString() + '/'
                        + enddate.getDay().toString() + '  '
                        + enddate.getHours().toString() + ':'
                        + enddate.getMinutes().toString();
                }
            }).catch( err=>{
                console.log(err);
            });
        }).catch( err=>{
            console.log(err);
        })
    };

    // 拒绝请求
    $scope.denyApply = function (apply) {
        $http({
            method: 'POST',
            url: constService.urls().denyApply,
            params:{
                'id': apply.mId
            }
        }).then( res=>{
            // 获得请求列表
            $http({
                method: 'POST',
                url: constService.urls().getAllApplies,
            }).then( res=>{
                console.log(res.data);
                $scope.applies = res.data;
                for (let i = 0; i < $scope.applies.length; i++){
                    let startdate = new Date($scope.applies[i].mStartDate);
                    $scope.applies[i].mStartDate = startdate.getFullYear().toString() + '/'
                        + startdate.getMonth().toString() + '/'
                        + startdate.getDay().toString() + '  '
                        + startdate.getHours().toString() + ':'
                        + startdate.getMinutes().toString();
                    let enddate = new Date($scope.applies[i].mEndDate);
                    $scope.applies[i].mEndDate = enddate.getFullYear().toString() + '/'
                        + enddate.getMonth().toString() + '/'
                        + enddate.getDay().toString() + '  '
                        + enddate.getHours().toString() + ':'
                        + enddate.getMinutes().toString();
                }
            }).catch( err=>{
                console.log(err);
            });
        }).catch( err=>{
            console.log(err);
        })
    };

}]);