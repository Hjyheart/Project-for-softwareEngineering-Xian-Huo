package com.example.service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

/**
 * Created by hongjiayong on 2016/12/21.
 */
@Service
public class QiniuService {

    private String access_key;
    private String secret_key;
    private String bucket;
    private String domain;
    private Auth auth;
    private Zone zone;
    private Configuration configuration;
    private UploadManager uploadManager;

    public QiniuService(){
        access_key = "z1jgIomPPX9dpfQQur9IcKxAscXjXn1Of4KvqCgA";
        secret_key = "wx-sSQb1FT2kiGRKilRgHk4IvCm_laFrDnT81_oh";
        bucket = "xianhuo";
        domain = "og1qwd3uz.bkt.clouddn.com";

        auth = Auth.create(access_key, secret_key);
        zone = Zone.autoZone();
        configuration = new Configuration(zone);
        uploadManager = new UploadManager(configuration);
    }

    public String getUpToken() {
        return auth.uploadToken(bucket);
    }

    public boolean uploadFile(String filePath){
        try{
            Integer gang = filePath.lastIndexOf("/");
            String key = filePath.substring(gang + 1, filePath.length());
            Response res = uploadManager.put(filePath, key, getUpToken());

            //打印返回的信息
            System.out.println(res.bodyString());
            return true;
        }catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
                return false;
            }
            return false;
        }
    }

    public String getDomain(){
        return domain;
    }

    public String createDownloadUrl(String url){
        return auth.privateDownloadUrl(url, 3600);
    }
}
