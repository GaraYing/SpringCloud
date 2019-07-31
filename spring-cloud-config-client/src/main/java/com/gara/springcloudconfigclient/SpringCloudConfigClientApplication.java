package com.gara.springcloudconfigclient;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudConfigClientApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SpringCloudConfigClientApplication.class, args);

		SpringApplication springApplication = new SpringApplication(SpringCloudConfigClientApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}

}
