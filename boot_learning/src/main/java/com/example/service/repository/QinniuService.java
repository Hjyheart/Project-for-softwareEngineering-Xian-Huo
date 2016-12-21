package com.example.service.repository;

import com.example.entity.ClubFile;
import com.qiniu.common.QiniuException;

/**
 * Created by hongjiayong on 2016/12/21.
 */
public interface QinniuService {
    String createUploadToken(String key);

    String createDownloadUrl(ClubFile clubFile);

    String createUploadToken();

    void deleteFile(String fileName) throws QiniuException;

    String downloadFile(ClubFile clubFile, String dir);


    void downloadFile(String url, String fileName, String dir);

    int getFileSize(ClubFile clubFile);

    void uploadFile(String path, String key);
}
