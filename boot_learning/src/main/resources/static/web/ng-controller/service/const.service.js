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

            // 首页
            indexInit: `${ServiceHost}/home/init`,

            // 用户中心
            myclubs: `${ServiceHost}/mycenter/myclubs`,
            myHostClub: `${ServiceHost}/mycenter/myhostclub`,

            // 社团
            clubdetail: `${ServiceHost}/club/detail`,
            getState: `${ServiceHost}/club/state`,
            addStudent: `${ServiceHost}/club/addstudent`,
            getClubStudent: `${ServiceHost}/club/getstudents`,
            deleteStudent: `${ServiceHost}/club/deletestudent`,
            getClubActivity: `${ServiceHost}/club/getactivity`,
            getClubComment: `${ServiceHost}/club/getcomment`,
            getClubFiles: `${ServiceHost}/club/getfiles`,
            addComment: `${ServiceHost}/club/addcomment`,
            vertifyClubHost: `${ServiceHost}/club/vertifyclubhost`,
            uploadClubFile: `${ServiceHost}/club/upload`
        }
    };

    this.urls = function (){
        return _const.urls;
    }
});