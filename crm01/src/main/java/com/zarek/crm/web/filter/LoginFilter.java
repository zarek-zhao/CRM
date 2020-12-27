package com.zarek.crm.web.filter;

import com.zarek.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入到验证有没有登录过的过滤器");

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String path = request.getServletPath();
        if("/login.jsp".equals(path) || "/setting/user/login.do".equals(path)){
            //这两个页面不应该拦截，直接放行
            chain.doFilter(req,resp);
        }else{
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if(user != null){
                chain.doFilter(req,resp);
            }else{
            /*
                重定向的路径怎么写？
                一律使用绝对路径
                转发：
                    使用的是一种特殊的绝对路径的使用方式，这种绝对路径前面不加/项目名，这种路径也称之为内部路径
                    /login.jsp
                重定向：
                    使用的是传统的绝对路径的写法，前面必须以/项目名开头，后面跟具体的资源路径
                    /crm/login.jsp

                为什么使用重定向，使用转发不行吗？
                    转发之后，路径会停留在老路径上，而不是跳转之后最新资源的路径
                    我们应该在为用户跳转到登录页的同时，将浏览器的地址应该自动设置为当前的登录页的路径

                request.getContextPath() /项目名
                request.
             */
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }
    }
}
