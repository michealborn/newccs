package zz.demo.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @Time 2019/4/3
 * @Author zlian
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter Init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter start");
        long startTime = new Date().getTime();
        chain.doFilter(request,response);
        System.out.println("filterTime:"+(new Date().getTime()-startTime));
        System.out.println("Filter Finish");
    }

    @Override
    public void destroy() {
        System.out.println("Filter Destroy");
    }
}
