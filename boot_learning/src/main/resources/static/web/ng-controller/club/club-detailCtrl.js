/**
 * Created by hongjiayong on 2016/12/12.
 */
app.controller('club-detailCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.isLogin = false;
    $scope.isApply = false;
    $scope.student;
    $scope.club;
    $scope.clubNum = 0;
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
            $scope.clubNum = $scope.club.mMemberNumber;
            // 获得主席名字
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
            // 获得社团活动
            $http({
                method: 'POST',
                url: constService.urls().getClubActivity,
                params:{
                    'c_id': $scope.club.mId
                }
            }).then( res=>{
                $scope.club.activity = res.data;

                for (let i = 0; i < $scope.club.activity.length; i++){
                    let date = new Date($scope.club.activity[i].mTime);
                    $scope.club.activity[i].time = date.getFullYear().toString() + '/'
                        + date.getMonth().toString() + '/'
                        + date.getDay().toString() + '  '
                        + date.getHours().toString() + ':'
                        + date.getMinutes().toString();
                    console.log($scope.club.activity[i].time);
                }
                console.log($scope.club.activity);
            }).catch( err=>{
                console.log(err);
            });
            // 获取社团的评论
            $http({
                method: 'POST',
                url: constService.urls().getClubComment,
                params:{
                    'c_id': $scope.club.mId
                }
            }).then( res=>{
                $scope.club.comment = res.data;
                console.log(res.data);
                for (let i = 0; i < $scope.club.comment.length; i++){
                    let date = new Date($scope.club.comment[i].mDate);
                    $scope.club.comment[i].time = date.getFullYear().toString() + '/'
                        + date.getMonth().toString() + '/'
                        + date.getDay().toString() + '  '
                        + date.getHours().toString() + ':'
                        + date.getMinutes().toString();
                }
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
                if (res.data === true){
                    $scope.isApply = true;
                    $scope.clubNum += 1;
                }
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
                $scope.clubNum -= 1;
            }).catch(err=> {
                alert("申请失败");
                console.log(err);
            });
        }
    };

    // 显示资源区
    $scope.showFile = function () {
       $('#source-list').transition('fade');
    };

    // 显示评论区
    $scope.showComment = function (){
        $('.active.item').removeClass('active');
        $('#first').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#comment').addClass('active');
    };

    // 显示活动
    $scope.showActivity = function (){
        $('.active.item').removeClass('active');
        $('#second').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#activity').addClass('active');
    };

    // 转到活动
    $scope.toActivity = function (activity) {
        window.location.href = '/activity/' + activity.mName;
    }
}]);