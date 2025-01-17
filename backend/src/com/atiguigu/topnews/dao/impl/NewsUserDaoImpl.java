package com.atiguigu.topnews.dao.impl;

import com.atiguigu.topnews.dao.BaseDao;
import com.atiguigu.topnews.dao.NewsUserDao;
import com.atiguigu.topnews.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao {
    @Override
    public NewsUser findByUsername(String username) {
        // 准备SQL
        String sql = "SELECT `uid`, `username`, `user_pwd` AS `userPwd`, `nick_name` AS `nickname` FROM `news_user` WHERE `username` = ?";
        // 调用BaseDAO公共查询方法
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        // 如果找到，返回集合中的第一个数据
        if (null != newsUserList && !newsUserList.isEmpty())
            return newsUserList.get(0);
        return null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = "SELECT `uid`, `username`, `user_pwd` AS `userPwd`, `nick_name` AS `nickname` FROM `news_user` WHERE `uid` = ?";
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, userId);
        if (null != newsUserList && !newsUserList.isEmpty())
            return newsUserList.get(0);
        return null;
    }
}
