package com.magfin.web.service;

import org.springframework.stereotype.Component;

/**
 * @Time 2019/4/13
 * @Author zlian
 */
@Component("roleService")
public class RoleServiceImpl {
    public boolean hasPermission(){
        return true;
    }
}
