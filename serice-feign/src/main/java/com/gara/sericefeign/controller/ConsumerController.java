package com.gara.sericefeign.controller;

import com.gara.dto.FileDTO;
import com.gara.sericefeign.req.FileDesc;
import com.gara.sericefeign.service.Dc2Service;
import com.gara.sericefeign.service.DcService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:47
 * @Version: 1.0
 **/
@RestController
public class ConsumerController {

    @Autowired
    private DcService dcService;
    @Autowired
    private Dc2Service dc2Service;

    @HystrixCommand(fallbackMethod = "defaultMethod")
    @GetMapping("feign/consumer")
    public String testFeign(){
        return dcService.consumer();
    }

    private String defaultMethod() {
        return "HystrixCommand Service Failed";
    }

    @GetMapping("feign/consumer2")
    public String testFeign2(){
        return dc2Service.consumer();
    }

    @PostMapping(value = "uploadFile")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file){
        return dcService.uploadFile(multipartFile2File(file));
    }

    @PostMapping(value = "uploadMultipartFile")
    public String uploadMultipartFile(@RequestParam(value = "file") MultipartFile file){
        return dcService.uploadMultipartFile(file);
    }

    @PostMapping(value = "uploadFileWithParams")
    public String uploadFileWithParams(@RequestParam(value = "file") MultipartFile file, @RequestParam String fileDesc){
        return dcService.uploadFileWithParams(file, fileDesc);
    }

    @PostMapping(value = "uploadFileWithDTO", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileWithDTO(@RequestParam(value = "file") MultipartFile file, FileDesc req){
        return dcService.uploadFileWithDTO(file, req);
    }

    private File multipartFile2File(MultipartFile multipartFile) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        File excelFile = null;
        try {
            excelFile = File.createTempFile(UUID.randomUUID().toString(), suffix);
            // MultipartFile to File
            multipartFile.transferTo(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelFile;
    }
}

