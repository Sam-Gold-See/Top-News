package com.atiguigu.topnews.dao;

import com.atiguigu.topnews.pojo.NewsUser;

public interface NewsUserDao {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 要查询的用户名
     * @return 找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUsername(String username);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId 要查询的用户id
     * @return 找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUid(Integer userId);

    /**
     * 将用户信息存入数据库
     *
     * @param newsUser 要注册的用户信息
     * @return 注册成功返回大于0的整数，失败返回0
     */
    int registUser(NewsUser newsUser);
}
