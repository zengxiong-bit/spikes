package com.whkj.spikes.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/helloServlet", initParams = {@WebInitParam(name = "msg", value = "hello")})
public class HelloServlet extends HttpServlet {

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer respBuff = new StringBuffer();
        Enumeration<String> headers = request.getHeaderNames();
        respBuff.append("Header: \r\n");
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = request.getHeader(headerName);

            String resp = headerName + " : " + headerValue;
            respBuff.append(resp + "\r\n");
        }


        respBuff.append("\r\n");
        respBuff.append("\r\n");

        Enumeration<String> params = request.getParameterNames();
        respBuff.append("Parameter: \r\n");
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            String paramValue = request.getParameter(paramName);

            String resp = paramName + " : " + paramValue;
            respBuff.append(resp + "\r\n");
        }

        PrintWriter out = null;
        try {
//            response.getOutputStream().write(respBuff.toString().getBytes());
            out = response.getWriter();
            out.println(respBuff);
            out.flush();
        }  finally {
            out.flush();
        }
    }

}
