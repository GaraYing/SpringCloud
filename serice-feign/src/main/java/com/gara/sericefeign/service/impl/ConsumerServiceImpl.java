package com.gara.sericefeign.service.impl;

import com.gara.eurekaprovider.req.FileDTO;
import com.gara.eurekaprovider.service.Dc2Service;
import com.gara.eurekaprovider.service.DcService;
import com.gara.sericefeign.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    private DcService dcService;
    @Resource
    private Dc2Service dc2Service;

    @Override
    public String consumer() {
        return dc2Service.consumer();
    }

    @Override
    public String uploadFile(File file) {
        return dcService.uploadFile(file);
    }

    @Override
    public String uploadMultipartFile(MultipartFile multipartFile) {
        return dcService.uploadMultipartFile(multipartFile);
    }

    @Override
    public String uploadFileWithParams(MultipartFile multipartFile, String fileDesc) {
        return dcService.uploadFileWithParams(multipartFile, fileDesc);
    }

    @Override
    public String uploadFileWithDTO(MultipartFile file, FileDTO req) {
        return dcService.uploadFileWithDTO(file, req);
    }
}
