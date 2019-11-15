package com.gara.sericefeign.service;

import com.gara.dto.FileDTO;
import com.gara.sericefeign.config.FeignConfiguration;
import com.gara.sericefeign.req.FileDesc;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @description:
 * @author: GaraYing
 * @createTime: 2019-10-21 14:42
 * @Version: 1.0
 **/
@Component
@FeignClient(name = "spring-cloud-eureka-provider",configuration = FeignConfiguration.class, fallback = DcServiceFallBackServiceImpl.class)
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
    String uploadFileWithDTO(@Param(value = "file") MultipartFile file, @Param(value = "data") FileDesc req);

//    public class MultipartSupportConfig {
//
//        @Autowired
//        private ObjectFactory<HttpMessageConverters> messageConverters;
//
//        @Bean
//        public Encoder feignFormEncoder () {
//            return new SpringFormEncoder(new SpringEncoder(messageConverters));
//        }
//    }
}
