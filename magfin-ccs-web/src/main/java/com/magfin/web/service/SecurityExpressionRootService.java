package com.magfin.web.service;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.core.Authentication;

/**
 * @Time 2019/4/14
 * @Author zlian
 */
public class SecurityExpressionRootService extends SecurityExpressionRoot {
    public SecurityExpressionRootService(Authentication authentication) {
        super(authentication);
    }
}
