package com.gara.sericefeign.controller;

import com.gara.eurekaprovider.req.FileDTO;
import com.gara.sericefeign.req.FileDesc;
import com.gara.sericefeign.service.ConsumerService;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:47
 * @Version: 1.0
 **/
@RestController
@RequestMapping(value = "feign/")
@ApiModel(description = "消费端控制器")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @HystrixCommand(fallbackMethod = "defaultMethod",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
            })
    @GetMapping("consumer")
    public String testFeign() {
        return consumerService.consumer();
    }

    @GetMapping("testHystrixCommand")
    public String testHystrixCommand() {
        return new TestCommand().execute();
    }

    private String defaultMethod() {
        return "HystrixCommand Service Failed";
    }

//    @GetMapping("feign/consumer2")
//    public String testFeign2() {
//        return dc2Service.consumer();
//    }

    @PostMapping(value = "uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return consumerService.uploadFile(multipartFile2File(file));
    }

    @PostMapping(value = "uploadMultipartFile")
    public String uploadMultipartFile(@RequestPart(value = "file") MultipartFile file) {
        return consumerService.uploadMultipartFile(file);
    }

    @PostMapping(value = "uploadFileWithParams")
    public String uploadFileWithParams(@RequestPart(value = "file") MultipartFile file, @RequestParam String fileDesc) {
        return consumerService.uploadFileWithParams(file, fileDesc);
    }

    @PostMapping(value = "uploadFileWithDTO", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileWithDTO(@RequestPart(value = "file") MultipartFile file, FileDesc req) {
        FileDTO fileDTO = new FileDTO();
        BeanUtils.copyProperties(req, fileDTO);
        return consumerService.uploadFileWithDTO(file, fileDTO);
    }

    @GetMapping(value = "customer/{id}")
    public Object uploadMultipartFile(@PathVariable(value = "id") Long id) {
        return consumerService.queryRemoteCustomer(id);
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

    /**
     * 编程方式实现HystrixCommand
     */
    private class TestCommand extends com.netflix.hystrix.HystrixCommand<String> {

        protected TestCommand() {
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"), 100);
        }

        @Override
        protected String run() throws Exception {
            int value = new Random().nextInt(200);

            System.out.println("TestHystrixCommand() costs " + value + " ms. ");

            Thread.sleep(1000);
            return "Hello World";
        }

        @Override
        protected String getFallback() {
            return ConsumerController.this.defaultMethod();
        }
    }
}

