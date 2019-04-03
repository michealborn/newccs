package zz.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Time 2019/4/3
 * @Author zlian
 */
@Aspect
//@Component
public class TimeAspect {
    //                                UserController的任何方法，里面有任何参数
    @Around("execution(* zz.demo.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("timeAspect start");

        long startTime = new Date().getTime();

        Object[] args = pjp.getArgs();
        for(Object arg:args){
            System.out.println("arg:"+arg);
        }

        Object proceed = pjp.proceed();

        System.out.println("timeAspect:"+(new Date().getTime()-startTime));

        System.out.println("timeAspect end");
        return proceed;
    }
}
