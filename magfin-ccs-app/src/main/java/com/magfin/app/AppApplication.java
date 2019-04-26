package com.magfin.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time 2019/4/1
 * @Author zlian
 */
@SpringBootApplication
//@MapperScan("com.magfin.app")
//@ImportResource(locations = {"classpath:spring-extend.xml"})
@RestController
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }

    @GetMapping("/hello")
    public String hello(String userName){
        return "hello";
    }
}