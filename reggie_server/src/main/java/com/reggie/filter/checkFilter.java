package com.reggie.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这里只演示过滤器的用法
 * 如果存在多个过滤器
 * 以注解方式配置的Filter过滤器，它的执行优先级是按时过滤器类名的
 * 自动排序确定的，类名排名越靠前，优先级越高
 */
@WebFilter(urlPatterns = "/*") // 配置过滤器要拦截的请求路径
@Slf4j
public class checkFilter implements Filter {
    //初始化方法，只调用一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器init初始化方法执行了");
    }
    //拦截到请求之后调用，调用多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //前置：强制转换为http协议的请求对象、响应对象 （转换原因：要使用子类中特有方法）
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取拦截到的url
        String url = request.getRequestURI().toString();
        log.info("过滤器拦截到请求：{}",url);
        //直接放行就是测试过滤器方法而已
        filterChain.doFilter(request,response);
        return;
        /*
        如果不放行执行下面的代码
        Result error = Result.error("NOT_LOGIN");
        手动转换
        String s = JSONObject.toJSONString(error);
        response.getWriter().write(s);
         */



    }
    //销毁方法，只调用一次
    @Override
    public void destroy() {
        System.out.println("过滤器destroy销毁方法执行了");
    }

}
