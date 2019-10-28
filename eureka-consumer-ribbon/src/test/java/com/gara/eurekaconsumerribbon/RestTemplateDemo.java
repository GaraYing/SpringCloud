package com.gara.eurekaconsumerribbon;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateDemo {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        Map<String, Object> data = restTemplate.getForObject("http://localhost:1001/actuator/env", Map.class);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("id", "232323");
        map.add("id", "232323dd");
        map.add("name", "zhangsan");
        System.out.println(map);
        System.out.println(map.toSingleValueMap());
        System.out.println(data);
    }
}
