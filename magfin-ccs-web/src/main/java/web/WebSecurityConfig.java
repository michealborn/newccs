package web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Time 2019/4/4
 * @Author zlian
 */
//springSecurity提供的适配器
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//        http.httpBasic()
                .and()
                .authorizeRequests()//开始对请求做授权
                .anyRequest()//任何请求
                .authenticated();//都需要身份认证
    }
}
