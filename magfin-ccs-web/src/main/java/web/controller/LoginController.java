package web.controller;

import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import web.dto.SimpleResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Time 2019/4/4
 * @Author zlian
 */
@RestController
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    /**
     * 当需要登录时跳转至此
     * @description requireAuthentication
     * @author 佛祖保佑后的最
     * @param request
     * @param response
     * @return java.lang.String
     * @time 2019/4/4
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null){
            String targetUrl = savedRequest.getRedirectUrl();
            logger.info("引发跳转的请求:"+targetUrl);
            //判断请求是否是html来的请求
//            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
//                redirectStrategy.sendRedirect(request,response,"/sign.html");
//            }else{
//
//            }
            redirectStrategy.sendRedirect(request,response,"/sign.html");
        }

        return new SimpleResponse("访问的服务需要登录，请去登录页。");

    }
}
