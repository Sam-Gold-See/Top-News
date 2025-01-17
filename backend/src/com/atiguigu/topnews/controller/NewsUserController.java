package com.atiguigu.topnews.controller;

import com.atiguigu.topnews.common.Result;
import com.atiguigu.topnews.common.ResultCodeEnum;
import com.atiguigu.topnews.pojo.NewsUser;
import com.atiguigu.topnews.service.NewsUserService;
import com.atiguigu.topnews.service.impl.NewsUserServiceImpl;
import com.atiguigu.topnews.utils.JwtUtil;
import com.atiguigu.topnews.utils.MD5Util;
import com.atiguigu.topnews.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController {
    private final NewsUserService newsUserService = new NewsUserServiceImpl();

    /**
     * 注册功能的业务接口实现
     *
     * @param req  HttpServletRequest对象，包含客户端的请求信息。
     * @param resp HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);
        NewsUser usedUser = newsUserService.findByUsername(newsUser.getUsername());
        Result result;
        if (null == usedUser && newsUserService.registUser(newsUser) != 0)
            result = Result.ok(null);
        else
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        WebUtil.writeJson(resp, result);
    }

    /**
     * 注册时检验用户名是否被占用的业务接口实现
     *
     * @param req  HttpServletRequest对象，包含客户端的请求信息。
     * @param resp HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        NewsUser newsUser = newsUserService.findByUsername(username);
        Result result;
        if (null == newsUser)
            result = Result.ok(null);
        else
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        WebUtil.writeJson(resp, result);
    }

    /**
     * 根据token口令获得用户信息的业务接口实现
     *
     * @param req  HttpServletRequest对象，包含客户端的请求信息。
     * @param resp HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) {
        // 获取请求中的token
        String token = req.getHeader("token");
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        // 校验token
        if (null != token && (!token.isEmpty()))
            if (!JwtUtil.isExpiration(token)) {
                Integer userId = JwtUtil.getUserId(token).intValue();
                NewsUser newsUser = newsUserService.findByUid(userId);
                if (null != newsUser) {
                    Map<String, Object> data = new HashMap();
                    newsUser.setUserPwd("");
                    data.put("user", newsUser);
                    result = Result.ok(data);
                }
            }
        WebUtil.writeJson(resp, result);
    }

    /**
     * 登录验证的业务接口实现
     *
     * @param req  HttpServletRequest对象，包含客户端的请求信息。
     * @param resp HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) {
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);

        Result result;
        NewsUser loginNewsUser = newsUserService.findByUsername(newsUser.getUsername());
        // 判断用户名
        if (null != loginNewsUser)
            // 判断密码
            if (loginNewsUser.getUserPwd().equals(MD5Util.encrypt(newsUser.getUserPwd()))) {
                // 密码正确
                Map<String, Object> data = new HashMap<>();
                // 生成token口令
                String token = JwtUtil.createToken(loginNewsUser.getUid().longValue());
                // 封装数据map
                data.put("token", token);
                // 封装结果
                result = Result.ok(data);
            } else
                // 封装密码错误结果
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        else
            // 封装用户名错误结果
            result = Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        // 响应结果
        WebUtil.writeJson(resp, result);
    }
}
