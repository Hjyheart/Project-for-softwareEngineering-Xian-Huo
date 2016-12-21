package com.example.service.repository;

import com.example.entity.ClubFile;
import com.example.service.QiniuProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by hongjiayong on 2016/12/21.
 */
@Service
public class QiniuServiceImpl implements QinniuService{

    @Autowired
    private QiniuProperties qiniuProperties;

    private Auth auth;
    private Zone zone;
    private Configuration configuration;
    private BucketManager bucketManager;

    @PostConstruct
    public void init() {
        this.auth = Auth.create(qiniuProperties.getAccess_key(), qiniuProperties.getSecret_key());
        this.zone = Zone.autoZone();
        this.configuration = new Configuration(zone);
        this.bucketManager = new BucketManager(auth, configuration);
    }

    @Override
    public String createUploadToken(String key) {
        System.out.println("===================");
        System.out.println(qiniuProperties.getBucket());
        System.out.println("===================");

        return auth.uploadToken(qiniuProperties.getBucket());
    }

    @Override
    public String createDownloadUrl(ClubFile clubFile) {
        String url = "http://" + qiniuProperties.getDomain() + "/" + clubFile.getmName();
        return url;
    }

    @Override
    public String createUploadToken() {
        return auth.uploadToken(qiniuProperties.getBucket());
    }

    @Override
    public void deleteFile(String fileName) throws QiniuException {
        bucketManager.delete(qiniuProperties.getBucket(), fileName);
    }

    @Override
    public String downloadFile(ClubFile clubFile, String dir) {
        String url = createDownloadUrl(clubFile);
        String fileName = clubFile.getmName();
        this.downloadFile(url, fileName, dir);
        return dir + "/" + fileName;
    }

    @Override
    public void downloadFile(String url, String fileName, String dir) {
        try {
            URL Url = new URL(url);

            URLConnection urlConnection = Url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();

            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());
            File file = new File(dir + "/"+ fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream outputStream = new FileOutputStream(file);
            int size = 0, len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                outputStream.write(buf, 0, size);
            }
            bin.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getFileSize(ClubFile clubFile) {
        int fileSize = 0;
        try {
            URL url = new URL(createDownloadUrl(clubFile));

            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            fileSize = httpURLConnection.getContentLength();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return fileSize;
        }
    }

    @Override
    public void uploadFile(String path, String key) {
        UploadManager uploadManager = new UploadManager(configuration);

        try {
            //调用put方法上传
            Response res = uploadManager.put(path, key, this.createUploadToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}
