/**
 * Created by hongjiayong on 2016/12/24.
 */
app.controller('ac-detailCtrl', ['$scope', '$http', 'constService', function($scope, $http, constService){
    $scope.student;
    $scope.isLogin = false;
    $scope.activity;
    $scope.isApply = false;
    this.$onInit = function () {
         $http({
             method: 'POST',
             url: constService.urls().loginIf
         }).then( res=> {
             if (res.data !== '') {
                 $scope.isLogin = true;
                 $scope.student = res.data;

                 // 获取活动和该学生的关系
                 $http({
                     method: 'POST',
                     url: constService.urls().registerIf,
                     params:{
                         'a_id': $('#activity-id').text(),
                         's_id': $scope.student.mId
                     }
                 }).then( res=>{
                    console.log(res.data);
                     if (res.data){
                         $scope.isApply = true;
                     }
                 }).catch( err=>{
                     console.log(err);
                 })
             }
         }).catch( err=>{
             console.log(err);
         });

         // 获取活动细节
         $http({
             method: 'POST',
             url: constService.urls().getActivityDetail,
             params:{
                 'id': $('#activity-id').text()
             }
         }).then( res=>{
             console.log(res.data);
             $scope.activity = res.data;
             let date = new Date($scope.activity.mTime);
             $scope.activity.mTime = date.getFullYear() + '/' + date.getMonth() + '/' + date.getDay()
                 + '/ ' + date.getHours() + ':' + date.getMinutes();

             for (let i = 0; i < $scope.activity.comments.length; i++){
                 let date = new Date($scope.activity.comments[i].mDate);
                 $scope.activity.comments[i].mDate = date.getFullYear() + '/' + date.getMonth() + '/' + date.getDay()
                     + '/ ' + date.getHours() + ':' + date.getMinutes();
             }
         }).catch( err=>{
             console.log(err);
         })
     };

    // 注册活动
    $scope.sendApply = function () {
        $http({
            method: 'POST',
            url: constService.urls().applyForActivity,
            params: {
                'a_id': $('#activity-id').text(),
                's_id': $scope.student.mId
            }
        }).then( res=>{
            console.log(res.data);
            // 获取活动最新数据
            if (res.data){
                $scope.isApply = true;
                $http({
                    method: 'POST',
                    url: constService.urls().getActivityDetail,
                    params:{
                        'id': $('#activity-id').text()
                    }
                }).then( res=>{
                    console.log(res.data);
                    $scope.activity.mPraise = res.data.mPraise;
                }).catch( err=>{
                    console.log(err);
                })
            }
        }).catch( err=>{
            console.log(err);
        })
    };

    // 退出活动
    $scope.sendQuit = function () {
        $http({
            method: 'POST',
            url: constService.urls().quitActivity,
            params: {
                'a_id': $('#activity-id').text(),
                's_id': $scope.student.mId
            }
        }).then( res=>{
            console.log(res.data);
            if (res.data){
                // 获取活动最新数据
                if (res.data) {
                    $scope.isApply = false;
                    $http({
                        method: 'POST',
                        url: constService.urls().getActivityDetail,
                        params: {
                            'id': $('#activity-id').text()
                        }
                    }).then(res=> {
                        console.log(res.data);
                        $scope.activity.mPraise = res.data.mPraise;
                    }).catch(err=> {
                        console.log(err);
                    })
                }
            }
        }).catch( err=>{
            console.log(err);
        })
    };
    
    // 添加评论
    $scope.addComment = function () {
        $http({
            method: 'POST',
            url: constService.urls().addActivityComment,
            params:{
                's_id': $scope.student.mId,
                'a_id': $('#activity-id').text(),
                'content': $('#comment-area').val()
            }
        }).then( res=>{
            console.log(res.data);
            let date = new Date(res.data.mDate);
            res.data.mDate = date.getFullYear().toString() + '/'
                + date.getMonth().toString() + '/'
                + date.getDay().toString() + '  '
                + date.getHours().toString() + ':'
                + date.getMinutes().toString();
            $scope.activity.comments.push(res.data);
            $('#comment-area').val('');
        }).catch( err=>{
            console.log(err);
        })
    }
}]);