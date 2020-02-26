package org.lanqiao.web;

import javax.servlet.*;
import java.io.IOException;

public class TestServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Test init....");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("Test config..........");
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("Test service......");
    }

    @Override
    public String getServletInfo() {
        System.out.println("Test info..........");
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Test destroy.....");

    }
}
