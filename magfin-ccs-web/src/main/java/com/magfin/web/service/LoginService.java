package com.magfin.web.service;

import com.magfin.web.dao.UsrUserMapper;
import com.magfin.web.model.UsrUser;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsrUserMapper usrUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("登录名:"+userName);
        List<GrantedAuthority> admin = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        //todo 查库
        UsrUser usrUser = usrUserMapper.selectByPrimaryKey("0009");
        //todo 判断逻辑
        /*
         isEnabled                  账户是否可用
         isAccountNonExpired        账户是否过期
         isCredentialsNonExpired    密码是否过期
         isAccountNonLocked         账户是否冻结
         */
        String passWord = passwordEncoder.encode("123");
        logger.info("密码:"+passWord);
        //从库里取出需要比较的真实用户名密码
        User user = new User(userName,passWord,
                true,
                true,
                true,
                true,
                admin);
        return user;
    }
}
