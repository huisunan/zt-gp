package com.zt.gp.compontent;

import com.alibaba.fastjson.JSON;
import com.zt.gp.bean.BaseResult;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        StringBuffer requestURL = request.getRequestURL();
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = requestURL.toString();
        if (!url.contains("/user/login") && !url.contains("/picture/add") && user == null){
            BaseResult result = BaseResult.error("-1","请先登录");
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = servletResponse.getWriter();
            writer.println(JSON.toJSONString(result));
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
