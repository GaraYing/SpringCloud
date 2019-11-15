package com.gara.eurekaclient;

import com.gara.dto.FileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: GaraYing
 * @create: 2018-09-19 16:28
 **/

@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Value("${server.port}")
    String port;

    @GetMapping("/dc")
    public String dc() {
        String services = "Service: " + discoveryClient.getServices();
        System.out.println(services);
        return services + " from "+ port;
    }

    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "file") MultipartFile file){
        return "server provided with ===>" + file.getOriginalFilename();
    }

    @PostMapping(value = "/uploadFileWithParams", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileWithParams(@NotNull @RequestParam MultipartFile file, @RequestParam String fileDesc){
        return "server provided with ===>" + file.getName() + " and desc is " + fileDesc;
    }

    @PostMapping(value = "/uploadFileWithDTO", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFileWithDTO(@NotNull @RequestParam(value = "file") MultipartFile file, FileDTO fileDTO){
        return "server provided with ===>" + file.getOriginalFilename() + " and desc is " + fileDTO.toString();
    }
}
