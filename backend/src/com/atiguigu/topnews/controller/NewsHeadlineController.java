package com.atiguigu.topnews.controller;

import com.atiguigu.topnews.common.Result;
import com.atiguigu.topnews.common.ResultCodeEnum;
import com.atiguigu.topnews.pojo.NewsHeadLine;
import com.atiguigu.topnews.service.NewsHeadLineService;
import com.atiguigu.topnews.service.impl.NewsHeadLineServiceImpl;
import com.atiguigu.topnews.utils.JwtUtil;
import com.atiguigu.topnews.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadLineController extends BaseController {
    private final NewsHeadLineService newsHeadLineService = new NewsHeadLineServiceImpl();

    /**
     * 发布新闻的业务接口实现
     *
     * @param req  HttpServletRequest对象，包含客户端的请求信息。
     * @param resp HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) {
        // 读取新闻信息
        NewsHeadLine newsHeadLine = WebUtil.readJson(req, NewsHeadLine.class);
        // 通过token获取发布者id
        String token = req.getHeader("token");
        Long userID = JwtUtil.getUserId(token);
        newsHeadLine.setPublisher(userID.intValue());
        // 将新闻存入数据库
        Result result = Result.ok(null);
        if (newsHeadLineService.addNewsHeadline(newsHeadLine) == 0)
            result = Result.build(null, ResultCodeEnum.UNKNOWN_ERROR);
        WebUtil.writeJson(resp, result);
    }

    /**
     * 修改新闻回显
     *
     * @param req  HttpServletRequest对象，包含客户端的请求信息。
     * @param resp HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) {
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        NewsHeadLine newsHeadLine = newsHeadLineService.findHeadlineByHid(hid);
        Map<String, Object> data = new HashMap<>();
        data.put("headline", newsHeadLine);
        WebUtil.writeJson(resp, Result.ok(data));
    }
}
