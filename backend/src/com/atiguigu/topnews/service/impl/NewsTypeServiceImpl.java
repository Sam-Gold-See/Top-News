package com.atiguigu.topnews.service.impl;

import com.atiguigu.topnews.dao.NewsTypeDao;
import com.atiguigu.topnews.dao.impl.NewsTypeDaoImpl;
import com.atiguigu.topnews.pojo.NewsType;
import com.atiguigu.topnews.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    private final NewsTypeDao newsTypeDao = new NewsTypeDaoImpl();

    @Override
    public List<NewsType> findAll() {
        return newsTypeDao.findAll();
    }
}
