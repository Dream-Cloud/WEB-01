package org.lanqiao.web;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 开发一个servlet 实现一个servlet接口
 */
public class HelloServlet implements Servlet {
    public HelloServlet() {
        System.out.println("servlet创建。。。。。。");
    }

    //表示servlet初始化 执行一次
    //作用：创建时机可控 默认是当第一次请求时 才会执行init方法创建servlet
    //当load-on-startup >= 0  则表示该servlet随着容器的启动而完成初始化 数字越小 初始化越早
    //当load-on-startup < 0  表示servlet初始化时机为第一次请求 初始化时机于数字大小无关 与请求顺序有关
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

        //获取web.xml中配置的该servlet的<servlet-name>helloServlet</servlet-name>的值
        String servletName = servletConfig.getServletName();
        System.out.println("servletName:" + servletName);
        //获取servlet中所有初始化参数的名称
        Enumeration<String> names = servletConfig.getInitParameterNames();
        while (names.hasMoreElements()) {
            System.out.println("paramName:" + names.nextElement());
        }
        //获取初始化参数的对应值
        String username = servletConfig.getInitParameter("username");
        System.out.println(username);
        String password = servletConfig.getInitParameter("password");
        System.out.println(password);
        //获取servletContext对象
        ServletContext application = servletConfig.getServletContext();
        //获取初始化参数
        String name = application.getInitParameter("name");
        String age = application.getInitParameter("age");
        System.out.println(name + "---" + age);
        //在servletContext中保存数据
        application.setAttribute("schoolName","中北大学");
        //获取HelloServlet中保存的schoolName数据
        String schoolName = (String) application.getAttribute("schoolName");
        System.out.println(schoolName);


        System.out.println("init.....");
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("get Servlet config...");
        return null;
    }
    //servlet的服务方法 是处理请求的 每一次请求都会执行servlet方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service.....");
    }

    @Override
    public String getServletInfo() {
        System.out.println("Servlet info....");
        return null;
    }
    //销毁（死亡）执行一次 servlet从servlet容器中卸载的时候才会执行
    @Override
    public void destroy() {
        System.out.println("destroy...");

    }
}
