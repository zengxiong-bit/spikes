package com.whkj.spikes.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author ksc
 */
public class MyFilter3 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(getClass().getName() + " init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(getClass().getName() + " doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println(getClass().getName() + " after doFilter");
    }

    @Override
    public void destroy() {
        System.out.println(getClass().getName() + " destroy");
    }
}
