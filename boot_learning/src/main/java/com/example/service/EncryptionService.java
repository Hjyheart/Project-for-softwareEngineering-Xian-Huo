package com.example.service;

import com.example.service.repository.StudentRepository;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.MessageDigest;

/**
 * Created by deado on 2016/11/9.
 */

@Service
public class EncryptionService {

    @Resource
    StudentRepository studentRepository;

    Character[] HEX_DIG = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private String encipherPassword(String password) throws Exception{
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytePw = password.getBytes("UTF8");
            messageDigest.update(bytePw);
            byte[] byteRes = messageDigest.digest();

            StringBuilder ret = new StringBuilder();

            for(int i=0; i<byteRes.length; i++){
                int tempContainer = byteRes[i];
                if(tempContainer < 0){
                    tempContainer+=256;
                }

                ret.append(HEX_DIG[tempContainer/16]);
                ret.append(HEX_DIG[tempContainer%16]);
            }

            return ret.toString();


        }catch(Exception e){
            throw e;
        }
    }

    public boolean comparePW(String studentId, String password) throws Exception{
        try{
            String md5Pw = this.studentRepository.findByMId(studentId).iterator().next().getmPassword();
            String ts = this.encipherPassword(password) + this.encipherPassword(studentId);
            if(0 == md5Pw.compareTo(
                    ts
            )){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            throw e;
        }
    }

    public String encipher(String password) throws Exception{
        try{
            return this.encipherPassword(password);
        }catch(Exception e){
            throw e;
        }
    }
}
