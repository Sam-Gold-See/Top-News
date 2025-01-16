package com.atiguigu.topnews.dao;

import com.atiguigu.topnews.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /**
     * 向数据库中查询所有头条类型并返回
     *
     * @return 查询的头条类型以List<NewsType>集合形式返回
     */
    List<NewsType> findAll();
}
