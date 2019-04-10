package com.magfin.web.controller;

import com.magfin.web.model.UsrResource;
import com.magfin.web.service.UsrResourceService;
import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.magfin.web.dto.SimpleResponse;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Time 2019/4/4
 * @Author zlian
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UsrResourceService usrResourceService;

    /**
     * 登录页面
     * @param
     * @return
     */
    @RequestMapping(value = "/")
    public String login(Model model,HttpServletRequest request){
        RuntimeException ex = (RuntimeException)request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        //如果已经登陆跳转到个人首页
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&&
                !authentication.getPrincipal().equals("anonymousUser")&&
                authentication.isAuthenticated()){
            return "redirct:/signIn";
        }
        if(ex!=null){
            model.addAttribute("error",ex.getMessage());
        }
        return "login";
    }




    @RequestMapping("/signIn")
    public ModelAndView signIn(Model model,HttpServletRequest request){
        UsrResource usrResource = new UsrResource();
        usrResource.setResType("1");
        List<UsrResource> usrResources = usrResourceService.queryListByObject(usrResource);
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("resourceList",usrResources);
        return new ModelAndView("/shouye");
    }
}
