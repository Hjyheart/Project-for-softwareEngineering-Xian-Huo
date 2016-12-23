package com.example.service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by hongjiayong on 2016/12/21.
 */
@Service
public class QiniuService {

    private String ACCESS_KEY = "z1jgIomPPX9dpfQQur9IcKxAscXjXn1Of4KvqCgA";
    private String SECRET_KEY = "wx-sSQb1FT2kiGRKilRgHk4IvCm_laFrDnT81_oh";
    private String BUCKET = "xianhuo";
    private String DOMAIN = "og1qwd3uz.bkt.clouddn.com";
    private Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private Zone zone = Zone.autoZone();
    private String LOCALFILEREPO = "";
    private Configuration configuration = new Configuration(zone);
    private UploadManager uploadManager = new UploadManager(configuration);

    public String getUpToken() {
        return auth.uploadToken(BUCKET);
    }

    public boolean uploadFile(String filePath){
        try{
            String key = filePath;
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

    private void getFileFromClient(MultipartFile mulFile, String fileName) throws Exception{
        try{
            //define local file iostream
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
            //copy iostream
            IOUtils.copy(mulFile.getInputStream(), fileOutputStream);

            //close the outstream
            fileOutputStream.close();

        }catch(Exception e){
            throw e;
        }
    }

    //public
    //encapsulate the all processes and make a sample method for foreign invoke
    public boolean storeFile(MultipartFile mulFile) throws Exception{
        try{
            //get file name
            String fileName = mulFile.getOriginalFilename();

            //retrieve file from client
            this.getFileFromClient(mulFile, this.LOCALFILEREPO+fileName);

            //upload to qiniu server
            this.uploadFile(this.LOCALFILEREPO+fileName);

            //delete file
            File FileToDelete = new File(this.LOCALFILEREPO + fileName);

            boolean res = FileToDelete.exists();
            if(!res){
                return false;
            }

            FileToDelete.delete();

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void deleteFile(String clubId, String fileName){
        try{
            BucketManager bucketManager = new BucketManager(this.auth, this.configuration);
            bucketManager.delete("Spring", clubId + "/" + fileName);
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


    public String getDomain(){
        return DOMAIN;
    }

    public String createDownloadUrl(String url){
        return auth.privateDownloadUrl(url, 3600);
    }
}
