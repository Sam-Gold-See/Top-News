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
