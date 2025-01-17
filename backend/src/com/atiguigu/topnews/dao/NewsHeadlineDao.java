package com.atiguigu.topnews.dao;

import com.atiguigu.topnews.pojo.HeadLinePageVo;
import com.atiguigu.topnews.pojo.HeadLineQueryVo;

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
}
