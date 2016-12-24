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
                if ($scope.isLogin === true){
                    // 得到用户和club的关系
                    $http({
                        method: "POST",
                        url: constService.urls().vertifyClubHost,
                        params:{
                            's_id': $scope.student.mId,
                            'c_id': $('#clubId').text()
                        }
                    }).then( res=>{
                        $scope.isHost = res.data;
                        if (!$scope.isHost){
                            window.location.href="/";
                        }
                    }).catch( err=>{
                        console.log(err);
                    })
                }else{
                    window.location.href="/login";
                }
            }else{
                window.location.href="/login";
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
            if (!$scope.club.mState){
                window.location.href = "/";
            }
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
            // 获取社团的学生列表
            $http({
                method: 'POST',
                url: constService.urls().getClubStudent,
                params:{
                    'c_id': $scope.club.mId
                }
            }).then( res=>{
                console.log(res.data);
                $scope.club.students = res.data;
            }).catch( err=>{
                console.log(err);
            })
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

    // 显示申请状况
    $scope.showApply = function () {
        $('.active.item').removeClass('active');
        $('#four').addClass('active');

        $('.active.tab.segment').removeClass('active');
        $('#apply').addClass('active');
    };

    // 上传文件
    $scope.uploadFile = function () {
        if ($('#file').val() === '' || $('#file').val() === null){
            return;
        }
        var formData = new FormData();
        formData.append('file', $('#file')[0].files[0]);
        formData.append('id', $scope.club.mId);
        console.log(formData);
        $.ajax({
            method: 'POST',
            url: constService.urls().uploadClubFile,
            cache: false,
            data: formData,
            processData: false,
            contentType: false
        }).done(function(res){
            console.log(res);
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
        }).catch( err=>{
            console.log(err);
        })
    };

    // 删除文件
    $scope.deleteFile = function (file) {
        $http({
            method: 'POST',
            url: constService.urls().deleteClubFile,
            params:{
                'filename': file.mName,
                'id': $scope.club.mId
            }
        }).then( res=>{
            console.log(res.data);
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
        }).catch( err=>{
            console.log(err);
        })
    };
    
    // 更换头像
    $scope.changeHead = function () {
        $('#change-head').modal({
            onApprove: function () {
                var formData = new FormData();
                formData.append('id', $scope.club.mId);
                formData.append('file', $('#head-file')[0].files[0]);
                $.ajax({
                    method: 'POST',
                    url: constService.urls().changeCover,
                    data: formData,
                    cache: false,
                    processData: false,
                    contentType: false
                }).done( function(res){
                    $http({
                        method: 'POST',
                        url: constService.urls().clubdetail,
                        params:{
                            'id': $('#clubId').text()
                        }
                    }).then( res=>{
                        $scope.club.mImgUrl = res.data.mImgUrl;
                    })
                }).catch( err=>{
                    console.log(err);
                })
            }
        }).modal('show');
    };

    // 编辑基本资料
    $scope.editbasicinfo = function(){
        $('#edit-basic').modal({
            onApprove: function () {
                if($('#club-new-name').val() === ''){
                    return;
                }
                $http({
                    method: 'POST',
                    url: constService.urls().editBasicInfo,
                    params:{
                        "id": $('#clubId').text(),
                        "name": $('#club-new-name').val(),
                        "des": $('#club-new-des').val()
                    }
                }).then(function (res) {
                    $http({
                        method: 'POST',
                        url: constService.urls().clubdetail,
                        params:{
                            'id': $('#clubId').text()
                        }
                    }).then( res=>{
                        $scope.club.mName = res.data.mName;
                        $scope.club.mDescription = res.data.mDescription
                    })
                })

            }
        }).modal('show');
    };

    // 添加活动
    $scope.addActivity = function () {
        $('#add-activity').modal({
            onApprove: function () {
                var formData = new FormData();
                var time = $('#activity-date').val() + '/' + $('#hour').val() + '/' + $('#minute').val();
                formData.append("id", $scope.club.mId);
                formData.append('time', time);
                formData.append('name', $('#activity-name').val());
                formData.append('location', $('#activity-location').val());
                formData.append('des', $('#activity-des').val());
                formData.append('contact', $('#activity-date').val());
                formData.append('file', $('#activity-cover')[0].files[0]);
                $.ajax({
                    method: 'POST',
                    url: constService.urls().addActivity,
                    data: formData,
                    cache: false,
                    processData: false,
                    contentType: false
                }).done(function () {
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
                });
            }
        }).modal('show');
    };

    // 转到活动详情页面
    $scope.toActivity = function (activity) {
        window.location.href = '/activity/' + activity.mId;
    };

    // 删除活动
    $scope.deleteActivity = function (activity) {
        $http({
            method: 'POST',
            url: constService.urls().deleteActivity,
            params:{
                'a_id': activity.mId,
                'c_id': $scope.club.mId
            }
        }).then( res=>{
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
        }).catch( err=>{
            console.log(err);
        })
    };

    // 申请场地
    $scope.applyForPosterArea = function () {

    };

    // 短信通知
    $scope.sendMessage = function () {
        $('#inform-message').modal({
            onApprove: function(){
                $http({
                    method:'POST',
                    url: constService.urls().sendMessageAll,
                    params:{
                        'c_id': $scope.club.mId,
                        'content': $('#message-content').val()
                    }
                }).then( res=>{
                    $('#inform-success').modal('show');
                }).catch( err=>{
                    console.log(err);
                })
            }
        }).modal('show');
    };

    // 通知活动
    $scope.inform = function(activity){
        $http({
            method: 'POST',
            url: constService.urls().informAll,
            params:{
                'a_id': activity.mId,
                'c_id': $scope.club.mId
            }
        }).then( res=>{
            $('#inform-success').modal('show');
        }).catch( err=>{
            console.log(err);
        })
    }
}]);