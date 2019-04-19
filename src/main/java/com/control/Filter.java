package com.control;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class Filter implements javax.servlet.Filter {

    public Filter() {

    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());

        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Methods","GET, DELETE, PUT, POST");
        ((HttpServletResponse) resp).setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
