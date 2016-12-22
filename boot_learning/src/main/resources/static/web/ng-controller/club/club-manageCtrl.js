/**
 * Created by hongjiayong on 2016/12/22.
 */
app.controller('club-manageCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.isLogin = false;
    $scope.student;
    $scope.club;
    $scope.clubNum = 0;
    $scope.chairman;
    $scope.isHost;

    this.$onInit = function() {
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

        if ($scope.isLogin === true){
            // 得到用户和club的关系
            $http({
                method: "POST",
                url: constService.urls().vertifyClubHost,
                params:{
                    's_id': $scope.student.mId,
                    'c_id': $scope.club.mId
                }
            }).then( res=>{
                $scope.isHost = res.data;
                if (!$scope.isHost){
                    window.location.href="/";
                }
            }).catch( err=>{
                console.log(err);
            })
        }

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
                    'id': $scope.club.mId
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
            // 获得社团的资源文件
            $http({
                method: 'POST',
                url: constService.urls().getClubFiles,
                params:{
                    'clubId': $scope.club.mId
                }
            }).then( res=>{
                $scope.club.files = res.data.club_files;
            }).catch( err=>{
                console.log(err);
            });
        }).catch( err =>{
            console.log(err);
        });

    };

    // 显示资源区
    $scope.showSource = function (){
        $('.active.item').removeClass('active');
        $('#first').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#source').addClass('active');
    };

    // 显示活动
    $scope.showActivity = function (){
        $('.active.item').removeClass('active');
        $('#second').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#activity').addClass('active');
    };

    // 显示学生列表
    $scope.showStudent = function(){
        $('.active.item').removeClass('active');
        $('#three').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#student').addClass('active');
    };

    // 上传文件
    $scope.uploadFile = function () {
          //$http({
          //
          //})
    };
}]);