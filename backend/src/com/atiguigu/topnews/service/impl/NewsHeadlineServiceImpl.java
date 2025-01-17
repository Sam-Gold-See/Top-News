package com.atiguigu.topnews.service.impl;

import com.atiguigu.topnews.dao.NewsHeadlineDao;
import com.atiguigu.topnews.dao.impl.NewsHeadLineDaoImpl;
import com.atiguigu.topnews.pojo.HeadLinePageVo;
import com.atiguigu.topnews.pojo.HeadLineQueryVo;
import com.atiguigu.topnews.service.NewsHeadLineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadLineServiceImpl implements NewsHeadLineService {
    private final NewsHeadlineDao newsHeadLineDao = new NewsHeadLineDaoImpl();

    public Map<String, Object> findPage(HeadLineQueryVo headlineQueryVo) {
        // 准备一个map用于装分页的五项数据
        Map<String, Object> pageInfo = new HashMap<>();
        // 分页查询本页数据
        List<HeadLinePageVo> pageData = newsHeadLineDao.findPageList(headlineQueryVo);
        // 分页查询满足记录的总数据量
        int totalSize = newsHeadLineDao.findPageCount(headlineQueryVo);
        // 页大小
        int pageSize = headlineQueryVo.getPageSize();
        // 总页码数
        int totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
        // 当前页码数
        int pageNum = headlineQueryVo.getPageNum();
        pageInfo.put("pageData", pageData);
        pageInfo.put("pageNum", pageNum);
        pageInfo.put("pageSize", pageSize);
        pageInfo.put("totalPage", totalPage);
        pageInfo.put("totalSize", totalSize);

        return pageInfo;
    }
}
