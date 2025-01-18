package com.atiguigu.topnews.service;

import com.atiguigu.topnews.pojo.HeadLineDetailVo;
import com.atiguigu.topnews.pojo.HeadLineQueryVo;
import com.atiguigu.topnews.pojo.NewsHeadLine;

import java.util.Map;

public interface NewsHeadLineService {
    /**
     * 分页查询头条新闻的业务接口实现
     *
     * @param headlineQueryVo 分页查询的五项数据对象
     * @return 返回pageData的键值对查询结果对象
     */
    Map<String, Object> findPage(HeadLineQueryVo headlineQueryVo);

    /**
     * 根据头条id，显示头条详情
     *
     * @param hid 头条id
     * @return 返回头条详情的HeadLineDetailVo类型对象
     */
    HeadLineDetailVo findHeadLineDetail(Integer hid);

    /**
     * 新增头条
     *
     * @param newsHeadLine 头条信息
     * @return 如果成功返回非0的整数，失败返回0
     */
    int addNewsHeadline(NewsHeadLine newsHeadLine);

    /**
     * 根据新闻id查询单个新闻
     *
     * @param hid 新闻id
     * @return 新闻实体类对象
     */
    NewsHeadLine findHeadlineByHid(Integer hid);

    /**
     * 更新新闻存储内容
     *
     * @param newsHeadLine 头条信息
     * @return 如果成功返回非0的整数，失败返回0
     */
    int updateNewsHeadline(NewsHeadLine newsHeadLine);
}
