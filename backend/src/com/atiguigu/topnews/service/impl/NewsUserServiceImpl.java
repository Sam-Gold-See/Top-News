package com.atiguigu.topnews.service.impl;

import com.atiguigu.topnews.dao.NewsUserDao;
import com.atiguigu.topnews.dao.impl.NewsUserDaoImpl;
import com.atiguigu.topnews.pojo.NewsUser;
import com.atiguigu.topnews.service.NewsUserService;

public class NewsUserServiceImpl implements NewsUserService {
    private final NewsUserDao newsUserDao = new NewsUserDaoImpl();

    @Override
    public NewsUser findByUsername(String username) {
        return newsUserDao.findByUsername(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return newsUserDao.findByUid(userId);
    }
}
