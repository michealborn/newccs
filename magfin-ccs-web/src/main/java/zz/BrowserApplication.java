package zz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time 2019/4/1
 * @Author zlian
 */
@SpringBootApplication
@RestController
public class BrowserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrowserApplication.class,args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
