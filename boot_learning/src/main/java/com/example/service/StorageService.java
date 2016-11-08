package com.example.service;

/**
 * Created by deado on 2016/11/3.
 */


<<<<<<< HEAD
import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
=======
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import com.example.entity.ClubFile;
import com.example.service.repository.ClubRepository;
import com.example.service.repository.FileRepository;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
>>>>>>> Hjyheart
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
<<<<<<< HEAD
import com.qiniu.util.StringMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
=======
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
>>>>>>> Hjyheart


@Service
@Transactional
public class StorageService {


<<<<<<< HEAD
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
=======
    @Resource
    private FileRepository fileRepository;
    @Resource
    private ClubRepository clubRepository;

    private String ACCESSKEY = "humAJtiHyVdoHfrjczH4lpOGKKDfbLdQ7vPIGT7z";
    private String SECRETKEY = "wvYG7d2LKYjPdtdMPtrrBZgGQIjhXNiEEK9olDGq";
    private String BUCKETNAME = "spring";
    private String LOCALFILEREPO = "filerepo/";
    private Auth   AUTH      = Auth.create(ACCESSKEY, SECRETKEY);


    //private method
    private String getUpToken(){
        return AUTH.uploadToken(BUCKETNAME);
    }

    private void uploadWithBreak(String fileUrl, String targetFileName) throws IOException{


        try{
            //set recorder
            Recorder fileRecorder = new FileRecorder("../../../../../../" + fileUrl);
            UploadManager uploadManager = new UploadManager(fileRecorder);

>>>>>>> Hjyheart
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
<<<<<<< HEAD
=======

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
    public void storeFile(MultipartFile mulFile, String ClubId) throws Exception{
        try{
            //get file name
            String fileName = mulFile.getOriginalFilename();

            //retrieve file from client
            this.getFileFromClient(mulFile, this.LOCALFILEREPO+fileName);

            //upload to qiniu server
            this.uploadWithBreak(this.LOCALFILEREPO+fileName, ClubId + "/" + fileName);

            //delete file
            File FileToDelete = new File(this.LOCALFILEREPO + fileName);

            boolean res = FileToDelete.exists();
            if(!res){
                throw new Exception();
            }

            FileToDelete.delete();

        }catch(Exception e){
            throw e;
        }
    }


    public String downloadFileUrl(String clubId, String fileName) throws Exception{

        try{
            Set<ClubFile> files = this.clubRepository.findByMId(clubId).iterator().next().getClubfiles();
            boolean existFlag = false;

            //check if the file exit
            Iterator<ClubFile> itrFile = files.iterator();
            while(itrFile.hasNext()){
                //find the file
                if(0 == fileName.compareTo(itrFile.next().getmName())){
                    existFlag = true;
                    break;
                }
            }

            //the file does not exist
            if(existFlag){
                throw new Exception();
            }

            String baseUrl = "http://bucketdomain/" + clubId + "/" + fileName;
            return this.AUTH.privateDownloadUrl(baseUrl);
        }catch(Exception e){
            throw e;
        }
    }


    public void deleteFile(String clubId, String fileName){
        try{
            BucketManager bucketManager = new BucketManager(this.AUTH);
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
>>>>>>> Hjyheart
}
