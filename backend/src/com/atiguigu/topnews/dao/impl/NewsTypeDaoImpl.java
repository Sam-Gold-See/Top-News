package com.atiguigu.topnews.dao.impl;

import com.atiguigu.topnews.dao.BaseDao;
import com.atiguigu.topnews.dao.NewsTypeDao;
import com.atiguigu.topnews.pojo.NewsType;

import java.util.List;

public class NewsTypeDaoImpl extends BaseDao implements NewsTypeDao {

    @Override
    public List<NewsType> findAll() {
        String sql = "SELECT `tid`, `tname` FROM `news_type`";
        return baseQuery(NewsType.class, sql);
    }
}
