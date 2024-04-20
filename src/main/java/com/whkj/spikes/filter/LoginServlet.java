package com.whkj.spikes.filter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String userName = "admin";
	private final String password = "admin123";

	@Override
	protected void doGet(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("name");
		String pwd = request.getParameter("pswd");
		
		if(userName.equals(user) && password.equals(pwd)){
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(30*60);
			response.addCookie(userName);
			PrintWriter out = response.getWriter();
			out.println("login succ");
			out.flush();
//			response.sendRedirect("LoginSuccess.jsp");
		}else{
			PrintWriter out = response.getWriter();
			out.println("login failed");
			out.flush();
		}

	}

}