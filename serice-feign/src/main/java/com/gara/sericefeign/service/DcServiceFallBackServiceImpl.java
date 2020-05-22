package com.gara.sericefeign.service;

import com.gara.sericefeign.req.FileDesc;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @description: 服务熔断
 * @author: GaraYing
 * @createTime: 2019-10-21 14:45
 * @Version: 1.0
 **/
public class DcServiceFallBackServiceImpl implements DcService{
    @Override
    public String consumer() {
        return "Get Service Failed";
    }

    @Override
    public String uploadFile(File file) {
        return "uploadFile failed" + file.getName();
    }

    @Override
    public String uploadMultipartFile(MultipartFile multipartFile) {
        return "uploadMultipartFile failed" + multipartFile.getOriginalFilename();
    }

    @Override
    public String uploadFileWithParams(MultipartFile multipartFile, String fileDesc) {
        return "uploadFileWithParams failed" + multipartFile.getOriginalFilename();
    }

    @Override
    public String uploadFileWithDTO(MultipartFile file, FileDesc req) {
        return "uploadFileWithDTO failed" + req.toString();
    }
}
