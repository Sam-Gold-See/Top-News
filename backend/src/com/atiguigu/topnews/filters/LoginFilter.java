package com.atiguigu.topnews.filters;

import com.atiguigu.topnews.common.Result;
import com.atiguigu.topnews.common.ResultCodeEnum;
import com.atiguigu.topnews.utils.JwtUtil;
import com.atiguigu.topnews.utils.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("token");
        boolean flag = null != token && !JwtUtil.isExpiration(token);
        if (flag)
            filterChain.doFilter(servletRequest, servletResponse);
        else
            WebUtil.writeJson((HttpServletResponse) servletResponse, Result.build(null, ResultCodeEnum.NOTLOGIN));
    }
}
