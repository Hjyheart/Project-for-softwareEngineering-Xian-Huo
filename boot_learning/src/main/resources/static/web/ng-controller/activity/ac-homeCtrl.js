/**
 * Created by hongjiayong on 2016/12/24.
 */
app.controller('ac-homeCtrl', ['$scope', '$http', 'constService', function($scope, $http, constService){
    $scope.isLogin = false;
    $scope.student;
    $scope.activities;
    $scope.myactivities;
    $scope.myhostactivities;
    this.$onInit = function () {
        $http({
            method: 'POST',
            url: constService.urls().loginIf
        }).then( res=>{
            if (res.data !== ''){
                $scope.isLogin = true;
                $scope.student = res.data;

                // 获得该学生举办的活动
                $http({
                    method: 'POST',
                    url: constService.urls().getMyHostActivity,
                    params:{
                        'id': $scope.student.mId
                    }
                }).then( res=>{
                    console.log(res.data);
                    $scope.myhostactivities = res.data;
                }).catch( err=>{
                    console.log(err);
                });

                // 获取该学生参加的活动信息
                $http({
                    method: 'POST',
                    url: constService.urls().getMyActivities,
                    params:{
                        'id': $scope.student.mId
                    }
                }).then( res=>{
                    console.log(res.data);
                    $scope.myactivities = res.data;
                }).catch( err=>{
                    console.log(err);
                });
            }else{
                $('#activity-list').removeClass('ten');
                $('#activity-list').removeClass('sixteen');
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

            for (let i = 0; i < $scope.activities.length; i++){
                let date = new Date($scope.activities[i].mTime);
                $scope.activities[i].mTime = date.getFullYear() + '/' + date.getMonth() + '/' + date.getDay()
                                + '/ ' + date.getHours() + ':' + date.getMinutes();
            }
        }).catch( err=>{
            console.log(err);
        })
    };

    // 显示我参与的活动
    $scope.showMyActivity = function () {
        $('.active.item').removeClass('active');
        $('#first').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#my-activity').addClass('active');
    };

    // 显示我组织的活动
    $scope.showMyHostActivity = function () {
        $('.active.item').removeClass('active');
        $('#second').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#my-organ-activity').addClass('active');
    };

    // 转到具体的detail
    $scope.toActivityDetail = function (activity) {
        window.location.href = '/activity/' + activity.mId;
    }
}]);