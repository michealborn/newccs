package zz.demo.service.impl;

import org.springframework.stereotype.Service;
import zz.demo.service.HelloService;

/**
 * @Time 2019/4/2
 * @Author zlian
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello "+name;
    }
}
