package fr.miage.mbds.taskservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "fr.miage.mbds.taskservice.client")
public class TaskserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskserviceApplication.class, args);
    }
}