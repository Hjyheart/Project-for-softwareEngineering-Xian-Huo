#coding:utf-8

import urllib
import urllib2
import re
import cookielib
import sys


class Login:

    __cookiejar = cookielib.CookieJar()
    __url_1 = 'http://4m3.tongji.edu.cn/eams/login.action'
    __url_2 = 'http://4m3.tongji.edu.cn/eams/samlCheck'
    __url_3 = 'https://ids.tongji.edu.cn:8443'
    __url_4 = 'http://4m3.tongji.edu.cn/eams/saml/SAMLAssertionConsumer'
    __menu_url = 'http://4m3.tongji.edu.cn/eams/home!submenus.action?menu.id='
    __welcome_url = 'http://4m3.tongji.edu.cn/eams/home!welcome.action'


    def Login(self, Id, Password):

        opener = urllib2.build_opener( urllib2.HTTPCookieProcessor(self.__cookiejar))
        urllib2.install_opener(opener)

        #get cookie
        urllib2.urlopen(urllib2.Request(self.__url_1))

        #get middle credential url
        middle_credential_text = urllib2.urlopen(urllib2.Request(self.__url_2)).read()
        middle_credential_url = re.findall('''url=(.*?)">''', middle_credential_text)[0]

        #get credential page
        credential_page = urllib2.urlopen(urllib2.Request(middle_credential_url)).read()
        final_credential_url = self.__url_3 + re.findall(''' <form method="POST" enctype="application/x-www-form-urlencoded" action="(.*?)">''', credential_page)[0]

        #get username&password post url
        final_credential_page = urllib2.urlopen(urllib2.Request(final_credential_url)).read()
        post_url = re.findall('''<form name="IDPLogin" enctype="application/x-www-form-urlencoded" method="POST" action="(.*?)" AUTOCOMPLETE="off">''', final_credential_page)[0]

        #post username and pw
        data = {'Ecom_User_ID': Id, 'Ecom_Password':Password}

        ret_page_1 = urllib2.urlopen( urllib2.Request(post_url, urllib.urlencode(data))).read()

        #get SamlAssertionCustomer url from the <javascript></javascript> label

        ret_url_1 = re.findall('''top.location.href='(.*?)';''', ret_page_1)

        #open the file and write the result
        if 0 == ret_url_1.__len__():
            print False
        else:
            print True






if __name__ == '__main__':
    checkObj=Login()
    checkObj.Login(sys.argv[1], sys.argv[2])





