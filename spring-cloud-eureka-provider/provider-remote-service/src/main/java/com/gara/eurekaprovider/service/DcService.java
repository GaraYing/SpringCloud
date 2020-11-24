package com.gara.eurekaprovider.service;

import com.gara.eurekaprovider.config.FeignConfiguration;
import com.gara.eurekaprovider.req.FileDTO;
import com.gara.eurekaprovider.service.impl.DcServiceFallBackServiceImpl;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:42
 * @Version: 1.0
 **/
@FeignClient(name = "${feign.client.name:spring-cloud-eureka-provider}", path = "/provider", contextId = "dc", url = "${feign.client.url:}",configuration = FeignConfiguration.class, fallback = DcServiceFallBackServiceImpl.class)
public interface DcService {

    @RequestLine("GET /dc")
    String consumer();

    @RequestLine("POST /uploadFile")
    @Headers("Content-Type: multipart/form-data")
    String uploadFile(@Param(value = "file") File file);

    @RequestLine("POST /uploadFile")
    @Headers("Content-Type: multipart/form-data")
    String uploadMultipartFile(@Param(value = "file") MultipartFile multipartFile);

    @RequestLine("POST /uploadFileWithParams")
    @Headers("Content-Type: multipart/form-data")
    String uploadFileWithParams(@Param(value = "file") MultipartFile multipartFile, @Param(value = "fileDesc") String fileDesc);

    @RequestLine("POST /uploadFileWithDTO")
    @Headers("Content-Type: multipart/form-data")
    String uploadFileWithDTO(@Param(value = "file") MultipartFile file, @Param(value = "data") FileDTO req);
}
