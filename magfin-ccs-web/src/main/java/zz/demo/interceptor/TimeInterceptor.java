package zz.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Time 2019/4/3
 * @Author zlian
 */
//@Component
public class TimeInterceptor implements HandlerInterceptor {
    @Override
    //方法调用前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle---");
        //类名
        System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
        //方法名
        System.out.println(((HandlerMethod)handler).getMethod().getName());

        request.setAttribute("startTime",new Date().getTime());
        return true;
    }

    @Override
    //进入方法调用，抛出异常不调用
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle---");
        Long startTime = (Long)request.getAttribute("startTime");
        System.out.println("timeInterceptor:"+(new Date().getTime()-startTime));
    }

    @Override
    //不管有没有异常 都调用
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion---");
        Long startTime = (Long)request.getAttribute("startTime");
        System.out.println("timeInterceptor:"+(new Date().getTime()-startTime));
        System.out.println("exception:"+e);
    }
}
