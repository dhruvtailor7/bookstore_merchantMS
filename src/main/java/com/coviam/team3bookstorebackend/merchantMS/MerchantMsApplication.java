package com.coviam.team3bookstorebackend.merchantMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MerchantMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MerchantMsApplication.class, args);
	}

}
