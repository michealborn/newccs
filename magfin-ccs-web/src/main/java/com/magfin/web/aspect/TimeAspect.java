package com.magfin.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.xml.ws.RequestWrapper;
import java.security.Principal;
import java.util.*;

/**
 * @Time 2019/4/3
 * @Author zlian
 */
@Aspect
@Component
public class TimeAspect {
    //                                UserController的任何方法，里面有任何参数
    @Around("execution(* com.magfin.web.controller.AccessController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("timeAspect start");

        long startTime = new Date().getTime();

        Object[] args = pjp.getArgs();
        HttpServletRequest request = null;
        for(Object arg : args){
            if(arg instanceof HttpServletRequest){
                request = (HttpServletRequest)arg;
            }
        }
        Object proceed = pjp.proceed(args);
        //方法结束后在model内加入
        if(proceed instanceof ModelAndView){
            ModelAndView mav = (ModelAndView)proceed;
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("accessOk",false);
            mav.addAllObjects(modelMap);
        }
//        UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
//        User principal = (User)userPrincipal.getPrincipal();
//        Collection<GrantedAuthority> authorities = principal.getAuthorities();
//        List<GrantedAuthority> admin = AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN");
//        boolean b = authorities.containsAll(admin);
//        String requestUrl = request.getRequestURI();
        return proceed;
    }
}