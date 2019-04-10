package com.magfin.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Time 2019/4/4
 * @Author zlian
 */
//springSecurity提供的适配器
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    //配置密码加解密bean，可写自己的
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/signIn")//让Security知道我现在用自定义请求发起登录
                .and()
                .authorizeRequests()//开始对请求做授权
                .antMatchers("/login.html").permitAll()
                .anyRequest()//任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable();
    }

    /**


     permitAll                                  永远返回true
     denyAll                                    永远返回false
     anonymous                                  当用户是anonymous返回true
     rememberMe                                 当用户是rememberMe返回true
     hasRole(role)                              用户拥有指定角色权限时返回true,需要在权限字符串前加"ROLE_"
     hasAnyRole([role1,role2])                  用户拥有任意一个指定角色权限时返回true,需要在权限字符串前加"ROLE_"
     hasAuthority(authority)                    用户拥有指定的权限时返回true
     hasAnyAuthority([authority1,authority2])   用户拥有任意一个指定角色权限时返回true

     连用表达式：access("hasRole('admin') and rememberMe()")
     **/
}
