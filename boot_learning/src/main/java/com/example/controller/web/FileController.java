package com.example.controller.web;

import com.example.entity.ClubFile;
import com.example.service.repository.FileRepository;
import com.example.service.repository.QinniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hongjiayong on 2016/12/21.
 */
@RestController
public class FileController {
    @Autowired
    @Qualifier("qiniuServiceImpl")
    private QinniuService qinniuService;

    @Autowired
    private FileRepository fileRepository;

    @RequestMapping(value = "/getToken")
    public Map getToken() {
        Map map = new HashMap();
        map.put("uptoken", qinniuService.createUploadToken());
        return map;
    }

    @RequestMapping(value = "/getKey")
    public Map getKey(@RequestParam(name = "name") String name) {
        Map map = new HashMap();

        ClubFile clubFile = new ClubFile();
        dataFile.setCreateTime(new Date());
        dataFile.setUpdateTime(new Date());
        dataFile.setStatus(0);
        dataFile.setOldname(name);
        dataFileRepository.save(dataFile);

        Integer point = name.lastIndexOf('.');
        String suffix = name.substring(point, name.length());
        String fileName = dataFile.getFileid() + suffix;
        dataFile.setName(fileName);
        dataFileRepository.save(dataFile);

        map.put("name", fileName);
        return map;
    }

    @RequestMapping(value = "/getDownloadUrl")
    public String getDownloadUrl(@RequestParam(name = "fileid") Long fileid) {
        return qiniuService.createDownloadUrl(dataFileRepository.findById(fileid));
    }


}
