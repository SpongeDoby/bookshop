package com.lqf.zfilters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//@WebFilter(urlPatterns = {"*.do","*.html"},initParams = {
//        @WebInitParam(name ="whiteList",value = "/bookshop/page.do?operateType=page&page=user/login"+","+
//        "/bookshop/user.do?null")})
public class SessionFilters implements Filter {
    List<String> whiteList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String temp1=filterConfig.getInitParameter("whiteList");
        String[]  temp2= temp1.split(",");
        whiteList = Arrays.asList(temp2);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String str=uri+"?"+queryString;
        if(whiteList.contains(str)){
            filterChain.doFilter(request,response);
            return;
        }else{
            HttpSession session = request.getSession();
            Object currUser = session.getAttribute("currUser");
            if(currUser==null){
                response.sendRedirect("page.do?operateType=page&page=user/login");
            }else{
                filterChain.doFilter(request,response);
            }
        }
    }
}
