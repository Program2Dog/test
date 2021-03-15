package com.duoerge.imge.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "NewFilter",urlPatterns = "/*")
public class NewFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws javax.servlet.ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        System.out.println("用户访问的路径是:"+httpServletRequest.getServletPath());
        System.out.println("用户访问的IP地址是:"+httpServletRequest.getRemoteAddr());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
