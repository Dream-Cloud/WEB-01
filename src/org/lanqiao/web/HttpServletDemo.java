package org.lanqiao.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取servletContext对象
        ServletContext application = this.getServletContext();
        //获取初始化参数
        String name = application.getInitParameter("name");
        String age = application.getInitParameter("age");
        System.out.println(name + "---" + age);
        //获取HelloServlet中保存的schoolName数据
        String schoolName = (String) application.getAttribute("schoolName");
        System.out.println(schoolName);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
