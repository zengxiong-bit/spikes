package com.whkj.spikes.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Order(1)
public class MyInterceptor1 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(getClass().getName() + " preHandle");

        if(handler instanceof org.springframework.web.method.HandlerMethod){
            org.springframework.web.method.HandlerMethod handlerMethod = (org.springframework.web.method.HandlerMethod) handler;
            System.out.println("handlerMethod.getMethod().getName() = " + handlerMethod.getMethod().getName());
        }
        response.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(getClass().getName() + " postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(getClass().getName() + " afterCompletion");
    }
}
