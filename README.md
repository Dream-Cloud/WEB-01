Java Servlet是和平台无关的服务器端组件，它运行在Servlet容器中。Servlet容器负责Servlet和客户的通信以及调用Servlet的方法，Servlet和客户的通信采用“请求/响应”的模式。

 Servlet，filter，listener统称为JavaWeb的三大组件，它属于动态资源。Servlet的作用是处理请求，服务器会把接收到的请求交给Servlet来处理，在Servlet中通常需要：

- 接收请求数据；
- 处理请求；
- 完成响应

# 1 servlet创建

需要完成以下2个步骤：

　　1、编写一个Java类，实现servlet接口。

　　2、在web.xml中配置servlet  把开发好的Java类部署到web服务器中 

开发一个servelt的步骤

创建第一个servlet程序

```java
//开发一个Serlvet  实现Serlvet接口 
public class HelloServlet  implements Servlet {     
    @Override    
    public void init(ServletConfig servletConfig) throws ServletException {        
        System.out.println("init。。。。。。。");    
    }     
    @Override    
    public ServletConfig getServletConfig() {        
        System.out.println("get Servlet config。。。。");
        return null;    
    }     
    @Override    
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {        
        System.out.println("service。。。。。。");    
    }     
    @Override    
    public String getServletInfo() {        
        System.out.println("servlet info。。。。。");
        return null;
    }     
    @Override    
    public void destroy() {
        System.out.println("destory。。。。。");
    } 
}
```

配置和映射Serlvet

```xml
<?xml version="1.0" encoding="UTF-8"?> 
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                             http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">    
    <servlet> <!--给当前的servlet配置一个名称-->        
        <servlet-name>helloSerlvet</servlet-name> 
        <!--指定要配置的servlet是哪一个具体的serlvet  此处需要使用全类名-->        
        <servlet-class>org.lanqiao.web.HelloServlet</servlet-class>
    </servlet> <!--    配置serlvet映射-->
    <servlet-mapping> 
        <!--映射的色弱略同的名称 需要对应上边的serlvet-name-->
        <servlet-name>helloSerlvet</servlet-name> 
        <!--serlvet请求规则-->
        <url-pattern>/hello</url-pattern>
    </servlet-mapping> 
</web-app>
```

请求的过程： tomcat

localhost:8080/

tomcat中 /默认会请求servlet中的一个默认的defaultServlet

执行到welcome-file-list

**url-pattern常用的配置方法：**

**/    :  表示项目的根路径** 

**/\* ：\*是通配符 无论后边是什么都无所谓 都可以匹配的到 \* 0-N个字符**

**多级路径配置**

```xml
	<!--配置serlvet映射-->
    <servlet-mapping>
		<!--映射的色弱略同的名称 需要对应上边的serlvet-name-->
        <servlet-name>helloSerlvet</servlet-name>
		<!--serlvet请求规则-->
        <url-pattern>/hello/*</url-pattern>
    </servlet-mapping>
```

**使用扩展名配置**

```xml
	<!-- 配置serlvet映射-->
    <servlet-mapping>
		<!-- 映射的色弱略同的名称 需要对应上边的serlvet-name-->
        <servlet-name>helloSerlvet</servlet-name>
		<!-- serlvet请求规则-->
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>
```

常用的 扩展名 .do  .action



# 2 Sevlet的生命周期：

1. 构造器：只被调用一次。当第一次请求Servlet时，创建Servlet的实例，调用构造器。说明Servlet是单实例的。

2. init ：表示servlet的初始化 执行一次

3. service：servlet的服务方法  是处理请求的 每一次请求都会执行service方法

4. destroy：销毁（死亡） 执行一次 serlvet从serlvet容器中卸载的时候 才会执行

init() 方法创建好servlet实例后立即被调用：

创建时机可控：

1. 默认情况下 是当第一次请求servlet的时候 才会执行该init 方法 创建servlet

```xml
<load-on-startup>0</load-on-startup>
```

2. 该参数>=0  则表示该serlvet随着容器的启动而完成初始化

   数字越小  初始化时机越早

3. 当参数<0 则servelt的初始化时机为第一次请求时

   此时初始话的时机 与数字大小无关 与请求顺序有关

每个Serlvet在整个声明周期中 有几个实例对象：

​	<font color=red>A  只有一个</font>

​	B  每次都会产生一个新的

**Servlet ---接口**

​	**GenericServlet---抽象类  ---service**

​		**HttpServlet --- 抽象类  ---无抽象方法 该类提供了对Http协议的支持**

**在开发中  sevlet的实现 采用继承HttpSerlvet，并重写doGet和doPost** 

```java
public class HttpServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //请求处理的核心代码
        System.out.println("请求处理。。。。。");
    }
}
```

# 3 Servlet API

## 3.1ServletConfig

- ServletConfig：init()方法的参数，它表示Servlet配置对象，它对应Servlet的配置信息，对应web.xml文件中的<servlet>元素。
- String getServletName()：获取Servlet在web.xml文件中的配置名称，即<servlet-name>指定的名称；
- Enumeration getInitParameterNames()：用来获取在web.xml中配置的所有初始化参数名称；
- String getInitParameter(String name)：用来获取在web.xml中配置的初始化参数，通过参数名来获取参数值；
- ServletContext getServletContext()：用来获取ServletContext对象，ServletContext会在后面讲解；

```java
@Override
public void init(ServletConfig servletConfig) throws ServletException {
    //获取web.xml中配置的该sevelt的
    //<servlet-name>helloSerlvet</servlet-name>的值
    String servletName =  servletConfig.getServletName();
    System.out.println("servletName：---"+servletName);
    //获取该sevlet中所有的 初始化参数的名称
    Enumeration<String> names =  servletConfig.getInitParameterNames();
    while(names.hasMoreElements()){
        System.out.println("parameterName ="+names.nextElement());
    }
    //获取初始化参数的对应的值
    String  username  =   servletConfig.getInitParameter("username");
    System.out.println(username );
    String password  = servletConfig.getInitParameter("password");
    System.out.println(password);
}
```

## 3.2 ServletContext

### 3.2.1 servlet概述

服务器会为每个应用创建一个ServletContext对象：

- ServletContext对象的创建是在服务器启动时完成的；
- ServletContext对象的销毁是在服务器关闭时完成的。
- ServletContext对象的作用是在整个Web应用的动态资源之间共享数据！例如在AServlet中向ServletContext对象中保存一个值，然后在BServlet中就可以获取这个值，这就是共享数据了。
- Servlet引擎为**每个WEB应用程序**都创建一个对应的ServletContext对象，ServletContext对象被包含在ServletConfig对象中，**调用ServletConfig.getServletContext方法可以返回ServletContext对象的引用**。
- **由于一个WEB应用程序中的所有Servlet都共享同一个ServletContext对象**，所以，ServletContext对象被称之为 application 对象（Web应用程序对象）。
- 功能：
  - **获取WEB应用程序的初始化参数 **
  - 记录日志 
  - **application域范围的属性 **
  - 访问资源文件 
  - WEB应用程序之间的访问 

### 3.2.2.获取ServletContext

- ServletConfig#getServletContext()；
- GenericServlet#getServletContext();
- HttpSession#getServletContext()
- ServletContextEvent#getServletContext()

```java
ServletContext application = servletConfig.getServletContext();
//获取初始化参数
String  name =  application.getInitParameter("name");
String age = application.getInitParameter("age");
System.out.println(name+"---"+age);
```

web.xml

```xml
<!-- ServletContext的初始化参数  可以被所有的servelt 所共享   -->
<context-param>
    <param-name>name</param-name>
    <param-value>jack</param-value>
</context-param>
<context-param>
    <param-name>age</param-name>
    <param-value>23</param-value>
</context-param>
    
<servlet>
    <!--给当前的servlet配置一个名称-->
    <servlet-name>helloSerlvet</servlet-name>
    <!--指定要配置的servlet是哪一个具体的serlvet  此处需要使用全类名-->
    <servlet-class>org.lanqiao.web.HelloServlet</servlet-class>
    <!--配置初始化参数 可以配置多个  该初始化参数只针对当前servelt有效-->
    <init-param>
        <param-name>username</param-name>
        <param-value>tom</param-value>
    </init-param>
    <init-param>
        <param-name>password</param-name>
        <param-value>123456</param-value>
    </init-param>
	<!--必须位于最后-->
    <load-on-startup>-100</load-on-startup>
</servlet>
<!--配置serlvet映射-->
<servlet-mapping>
    <!--映射的色弱略同的名称 需要对应上边的serlvet-name-->
    <servlet-name>helloSerlvet</servlet-name>
    <!--serlvet请求规则-->
    <url-pattern>/hello</url-pattern>
</servlet-mapping>
```

### 2.2.3 javaWEB的域对象

ServletContext是JavaWeb四大域对象之一：

- PageContext；
- ServletRequest；
- HttpSession；
- ServletContext

所有域对象都有存取数据的功能，因为域对象内部有一个Map，用来存储数据，下面是ServletContext对象用来操作数据的方法：

- void setAttribute(String name, Object value)：用来存储一个对象，也可以称之为存储一个域属性，例如：servletContext.setAttribute(“xxx”, “XXX”)，在ServletContext中保存了一个域属性，域属性名称为xxx，域属性的值为XXX。请注意，如果多次调用该方法，并且使用相同的name，那么会覆盖上一次的值，这一特性与Map相同；
- Object getAttribute(String name)：用来获取ServletContext中的数据，当前在获取之前需要先去存储才行，例如：String value = (String)servletContext.getAttribute(“xxx”);，获取名为xxx的域属性；
- void removeAttribute(String name)：用来移除ServletContext中的域属性，如果参数name指定的域属性不存在，那么本方法什么都不做；
- Enumeration getAttributeNames()：获取所有域属性的名称；
