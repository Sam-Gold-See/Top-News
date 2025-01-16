package com.atiguigu.topnews.controller;

import com.atiguigu.topnews.common.Result;
import com.atiguigu.topnews.pojo.NewsType;
import com.atiguigu.topnews.service.NewsTypeService;
import com.atiguigu.topnews.service.impl.NewsTypeServiceImpl;
import com.atiguigu.topnews.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private final NewsTypeService typeService = new NewsTypeServiceImpl();

    /**
     * 查询所有头条类型的业务接口实现
     *
     * @param req：HttpServletRequest对象，包含客户端的请求信息。
     * @param resp：HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) {
        // 查询所有的新闻类型，装入Result响应给客户端
        List<NewsType> newsTypeList = typeService.findAll();
        // 将查询结果以列表形式放入JSON串
        WebUtil.writeJson(resp,Result.ok(newsTypeList));
    }
}
