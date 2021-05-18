package com.insignia.customerservice.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfiguration {

    private RestTemplate client;

    @Bean
    @LoadBalanced
    public RestTemplate getClient(){
        client=new RestTemplate();
        return client;
    }
}
