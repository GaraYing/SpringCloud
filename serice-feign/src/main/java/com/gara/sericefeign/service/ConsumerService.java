package com.gara.sericefeign.service;

import com.gara.eurekaprovider.req.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @description: 消费服务
 * @author:  GaraYing
 * @createTime: 2020/6/4 19:53
 * @Version: 1.0
**/

public interface ConsumerService {

    String consumer();

    String uploadFile(File file);

    String uploadMultipartFile(MultipartFile multipartFile);

    String uploadFileWithParams (MultipartFile multipartFile, String fileDesc);

    String uploadFileWithDTO(MultipartFile file, FileDTO req);

    Object queryRemoteCustomer(Long id);
}
