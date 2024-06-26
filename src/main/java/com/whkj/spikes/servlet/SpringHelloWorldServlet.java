package com.whkj.spikes.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class SpringHelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SpringHelloWorldServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println("SpringHelloWorldServlet: GET METHOD");
            out.flush();
        } finally {
            if (!Objects.isNull(out)) {
                out.close();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.println("SpringHelloWorldServlet: POST METHOD");
            out.flush();
        } finally {
            if (!Objects.isNull(out)) {
                out.close();
            }
        }
    }

}
