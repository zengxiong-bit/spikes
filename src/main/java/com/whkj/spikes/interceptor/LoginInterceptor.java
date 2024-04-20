package com.whkj.spikes.interceptor;

import com.whkj.spikes.bo.LoginUser;
import com.whkj.spikes.service.PassportService;
import com.whkj.spikes.util.CookieUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {
	
	private PassportService passportService;

	public LoginInterceptor(PassportService passportService) {
		this.passportService = passportService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//在Handler执行之前处理 判断用户是否登录
		//从cookie中取token
		String token = CookieUtils.getCookieValue(request, "KSC_TOKEN");
		//根据token换取用户信息，调用登录系统的接口
		Pair<Boolean, LoginUser> pair = passportService.loginCheck(token);
		boolean pass = pair.getLeft();
		LoginUser loginUser = pair.getRight();
		//取不到用户信息
		if (!pass) {
			//跳转到登录页面，把用户请求的url作为参数传递给登录页面。
			request.setAttribute("errMsg", "未登录，请先登录.");
			response.sendRedirect("/passport/login" + "?redirect=" + request.getRequestURL());
			//返回false
			return false;
		}
		//取到用户信息，放行
		//把用户信息放入Request
		request.setAttribute("loginUser", loginUser);
		//返回值决定handler是否执行。true：执行，false：不执行。
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// handler执行之后，返回ModelAndView之前

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 返回ModelAndView之后。
		//响应用户之后。

	}

}
