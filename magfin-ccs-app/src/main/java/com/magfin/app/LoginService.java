package com.magfin.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Time 2019/4/9
 * @Author zlian
 */
@Component
public class LoginService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
//    WebSecurityConfig里配置的密码加解密方法
    private PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            logger.info("登录名:"+username);
            List<GrantedAuthority> admin = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN");
//
            //从库里取出需要比较的真实用户名密码
            User user = new User(username,"123456",/*数据库里的密码*/
                    true,
                    true,
                    true,
                    true,
                    admin/*用户权限*/);
            return user;
    }
}
