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

            // 首页
            indexInit: `${ServiceHost}/home/init`
        }
    };

    this.urls = function (){
        return _const.urls;
    }
});