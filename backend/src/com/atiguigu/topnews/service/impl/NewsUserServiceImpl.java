package com.atiguigu.topnews.service.impl;

import com.atiguigu.topnews.dao.NewsUserDao;
import com.atiguigu.topnews.dao.impl.NewsUserDaoImpl;
import com.atiguigu.topnews.pojo.NewsUser;
import com.atiguigu.topnews.service.NewsUserService;
import com.atiguigu.topnews.utils.MD5Util;

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

    @Override
    public int registUser(NewsUser newsUser) {
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        return newsUserDao.registUser(newsUser);
    }
}
