package com.magfin.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.HashSet;
import java.util.Set;

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


    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        //配置模板
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        // 使用HTML的模式，也就是支持HTML5的方式，使用data-th-*的H5写法来写thymeleaf的标签语法
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // 页面不产生缓存，和热部署前端
        templateResolver.setCacheable(false);

        return templateResolver;
    }
//    @Autowired
//    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/loginzzz")
                .and()
                .authorizeRequests()//开始对请求做授权
                .antMatchers("/login.html").permitAll()
                .anyRequest()//任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable();




       /* http
            .authorizeRequests()
            .antMatchers("/me").hasAnyRole("MEMBER","SUPER_ADMIN")//个人首页只允许拥有MENBER,SUPER_ADMIN角色的用户访问
            .anyRequest().authenticated()
                .and()
            .formLogin()
            .loginPage("/").permitAll()//这里程序默认路径就是登陆页面，允许所有人进行登陆
            .loginProcessingUrl("/log")//登陆提交的处理url
            .failureForwardUrl("/?error=true")//登陆失败进行转发，这里回到登陆页面，参数error可以告知登陆状态
            .defaultSuccessUrl("/me")//登陆成功的url，这里去到个人首页
                .and()
            .logout()
            .logoutUrl("/logout").permitAll()//登出的url，security会拦截这个url进行处理，所以登出不需要我们实现
            .logoutSuccessUrl("/?logout=true")//登出成功后url，logout告知登陆状态
                .and()
            .rememberMe()
            .tokenValiditySeconds(604800)//记住我功能，cookies有限期是一周
            .rememberMeParameter("remember-me")//登陆时是否激活记住我功能的参数名字，在登陆页面有展示
            .rememberMeCookieName("workspace");//cookies的名字，登陆后可以通过浏览器查看cookies名字
*/

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
