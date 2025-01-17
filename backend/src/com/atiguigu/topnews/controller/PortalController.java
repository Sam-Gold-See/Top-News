package com.atiguigu.topnews.controller;

import com.atiguigu.topnews.common.Result;
import com.atiguigu.topnews.pojo.HeadLineDetailVo;
import com.atiguigu.topnews.pojo.HeadLineQueryVo;
import com.atiguigu.topnews.pojo.NewsType;
import com.atiguigu.topnews.service.NewsHeadLineService;
import com.atiguigu.topnews.service.NewsTypeService;
import com.atiguigu.topnews.service.impl.NewsHeadLineServiceImpl;
import com.atiguigu.topnews.service.impl.NewsTypeServiceImpl;
import com.atiguigu.topnews.utils.WebUtil;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/portal/*")
public class PortalController extends BaseController {
    private final NewsTypeService typeService = new NewsTypeServiceImpl();

    private final NewsHeadLineService newsHeadlineService = new NewsHeadLineServiceImpl();

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
        WebUtil.writeJson(resp, Result.ok(newsTypeList));
    }

    /**
     * 分页带条件查询新闻
     *
     * @param req：HttpServletRequest对象，包含客户端的请求信息。
     * @param resp：HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) {
        HeadLineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadLineQueryVo.class);
        // 查询分页五项数据
        Map<String, Object> pageInfo = newsHeadlineService.findPage(headlineQueryVo);
        // 将分页五项数据放入PageInfoMap
        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("pageInfo", pageInfo);
        // 响应JSON
        WebUtil.writeJson(resp, Result.ok(pageInfoMap));
    }

    /**
     * 查询单个新闻详情
     *
     * @param req：HttpServletRequest对象，包含客户端的请求信息。
     * @param resp：HttpServletResponse对象，用于向客户端发送响应。
     */
    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) {
        // 获取要查询的详情新闻id
        Integer hid = Integer.parseInt(req.getParameter("hid"));
        // 查询新闻详情
        HeadLineDetailVo headLineDetailVo = newsHeadlineService.findHeadLineDetail(hid);
        // 封装data内容
        Map<String, Object> data = new HashMap<>();
        data.put("headline", headLineDetailVo);
        //响应JSON
        WebUtil.writeJson(resp, Result.ok(data));
    }
}
