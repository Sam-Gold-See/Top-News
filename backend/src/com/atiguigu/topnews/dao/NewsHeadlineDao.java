package com.atiguigu.topnews.dao;

import com.atiguigu.topnews.pojo.HeadLineDetailVo;
import com.atiguigu.topnews.pojo.HeadLinePageVo;
import com.atiguigu.topnews.pojo.HeadLineQueryVo;
import com.atiguigu.topnews.pojo.NewsHeadLine;

import java.util.List;

public interface NewsHeadlineDao {

    /**
     * 根据查询条件，查询当前页数据
     *
     * @param headlineQueryVo 分页查询的五项数据对象
     * @return 返回当前页数据
     */
    List<HeadLinePageVo> findPageList(HeadLineQueryVo headlineQueryVo);

    /**
     * 根据查询条件，查询满足条件的记录数
     *
     * @param headlineQueryVo 分页查询的五项数据对象
     * @return 返回满足条件的记录数
     */
    int findPageCount(HeadLineQueryVo headlineQueryVo);

    /**
     * 增加新闻的信息浏览量
     *
     * @param hid 头条id
     */
    void increasePageViews(Integer hid);

    /**
     * 多表查询新闻详情
     *
     * @param hid 头条id
     * @return 新闻详情HeadLineDetailVo对象
     */
    HeadLineDetailVo findHeadlineDetail(Integer hid);

    /**
     * 头条存入数据库
     *
     * @param newsHeadLine 头条信息
     * @return 如果成功返回非0的整数，失败返回0
     */
    int addNewsHeadline(NewsHeadLine newsHeadLine);
}
