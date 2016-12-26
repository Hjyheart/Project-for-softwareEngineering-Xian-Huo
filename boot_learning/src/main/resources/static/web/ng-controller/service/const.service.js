/**
 * Created by hongjiayong on 2016/12/9.
 */
app.service('constService', function(){
    var ServiceHost = 'http://localhost:8080';
    var _const = {
        urls:{
            // 用户认证
            loginIf: `${ServiceHost}/login/if`,
            vertify: `${ServiceHost}/login/vertify`,
            compelete: `${ServiceHost}/login/compelete`,
            getstudent: `${ServiceHost}/mycenter/getstu`,
            changeHead: `${ServiceHost}/mycenter/changehead`,

            // 首页
            indexInit: `${ServiceHost}/home/init`,

            // 用户中心
            myclubs: `${ServiceHost}/mycenter/myclubs`,
            myHostClub: `${ServiceHost}/mycenter/myhostclub`,

            // 社团
            addClub: `${ServiceHost}/club/applyforclub`,
            clubdetail: `${ServiceHost}/club/detail`,
            getState: `${ServiceHost}/club/state`,
            addStudent: `${ServiceHost}/club/addstudent`,
            getClubStudent: `${ServiceHost}/club/getstudents`,
            deleteStudent: `${ServiceHost}/club/deletestudent`,
            getClubActivity: `${ServiceHost}/club/getactivity`,
            getClubComment: `${ServiceHost}/club/getcomment`,
            getClubFiles: `${ServiceHost}/club/getfiles`,
            addClubComment: `${ServiceHost}/club/addcomment`,
            vertifyClubHost: `${ServiceHost}/club/vertifyclubhost`,
            uploadClubFile: `${ServiceHost}/club/upload`,
            deleteClubFile: `${ServiceHost}/club/deletefile`,
            changeCover: `${ServiceHost}/club/changecover`,
            editBasicInfo: `${ServiceHost}/club/editbasicinfo`,
            sendMessageAll: `${ServiceHost}/club/informAll`,
            getAllClub: `${ServiceHost}/club/all`,
            applyGround: `${ServiceHost}/club/applyground`,
            applyPoster: `${ServiceHost}/club/applyposter`,
            getApplies: `${ServiceHost}/club/getapplies`,

            // 活动
            addActivity: `${ServiceHost}/club/addactivity`,
            deleteActivity: `${ServiceHost}/activity/delete`,
            informAll: `${ServiceHost}/activity/informAll`,
            getMyActivities: `${ServiceHost}/mycenter/myactivities`,
            getAllActivities: `${ServiceHost}/activity/allactivity`,
            getMyHostActivity: `${ServiceHost}/mycenter/myhostactivities`,
            getActivityDetail: `${ServiceHost}/activity/detail`,
            registerIf: `${ServiceHost}/activity/isregister`,
            applyForActivity: `${ServiceHost}/activity/apply`,
            quitActivity: `${ServiceHost}/activity/quit`,
            addActivityComment: `${ServiceHost}/activity/addcomment`,
            editActivity: `${ServiceHost}/activity/edit`,

            // 管理页面
            rejectClub: `${ServiceHost}/club/reject`,
            acceptClub: `${ServiceHost}/club/accept`,
            getAllApplies: `${ServiceHost}/club/getallapplies`,
            approveApply: `${ServiceHost}/club/approveapply`,
            denyApply: `${ServiceHost}/club/denyapply`
        }
    };

    this.urls = function (){
        return _const.urls;
    }
});