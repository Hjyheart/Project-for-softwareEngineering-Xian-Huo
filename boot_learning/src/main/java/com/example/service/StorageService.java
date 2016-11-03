package com.example.service;

/**
 * Created by deado on 2016/11/3.
 */


import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StorageService {


    private String ACCESSKEY = "humAJtiHyVdoHfrjczH4lpOGKKDfbLdQ7vPIGT7z";
    private String SECRETKEY = "wvYG7d2LKYjPdtdMPtrrBZgGQIjhXNiEEK9olDGq";
    private String BUCKETNAME = "spring";
    private Auth   AUTH      = Auth.create(ACCESSKEY, SECRETKEY);


    public String getUpToken(){
        return AUTH.uploadToken(BUCKETNAME);
    }



    public void uploadWithBreak(String fileUrl, String targetFileName) throws IOException{
        //set recorder
        Recorder fileRecorder = new FileRecorder(fileUrl);
        UploadManager uploadManager = new UploadManager(fileRecorder);

        try{
            //Response
            Response res = uploadManager.put(fileUrl, targetFileName, getUpToken());
            System.out.println(res.bodyString());
        }catch(QiniuException e){
            Response r = e.response;
            // request fail info
            System.out.println(r.toString());
            try {
                //response text info
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}
