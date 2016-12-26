/**
 * Created by hongjiayong on 2016/12/24.
 */
app.controller('club-homeCtrl', ['$scope', '$http', 'constService', function ($scope, $http, constService) {
    $scope.isLogin = false;
    $scope.clubs;

    this.$onInit = function () {
        $http({
            method: 'POST',
            url: constService.urls().loginIf
        }).then( res=>{
            if (res.data !== '') {
                $scope.isLogin = true;
                $scope.student = res.data;

                // 获取该学生参加的社团
                $http({
                    method: 'POST',
                    url: constService.urls().myclubs,
                    params:{
                        'id': $scope.student.mId
                    }
                }).then( res=>{
                    console.log(res.data);
                    $scope.myclubs = res.data;
                }).catch( err=>{
                    console.log(err);
                });

                // 获取该学生主办的社团
                $http({
                    method: 'POST',
                    url: constService.urls().myHostClub,
                    params:{
                        'id': $scope.student.mId
                    }
                }).then( res=>{
                    console.log(res.data);
                    $scope.myhostclubs = res.data.host_clubs;
                }).catch( err=>{
                    console.log(err);
                });
            }else{
                $('#club-list').removeClass('ten');
                $('#club-list').removeClass('sixteen');
            }
        }).catch( err=>{
            console.log(err);
        });

        // 获取所有社团
        $http({
            method: 'POST',
            url: constService.urls().getAllClub
        }).then( res=>{
            console.log(res.data);
            $scope.clubs = res.data;
        }).catch( err=>{
            console.log(err);
        });

    };

    // 显示我参与的社团
    $scope.showMyClub = function () {
        $('.active.item').removeClass('active');
        $('#first').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#my-club').addClass('active');
    };

    // 显示我组织的社团
    $scope.showMyHostClub = function () {
        $('.active.item').removeClass('active');
        $('#second').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#my-organ-club').addClass('active');
    };

    // 转到具体俱乐部
    $scope.toClubDetail = function (club) {
        window.location.href = '/club/' + club.mId;
    }
}]);