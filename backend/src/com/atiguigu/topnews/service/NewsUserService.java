package com.atiguigu.topnews.service;

import com.atiguigu.topnews.pojo.NewsUser;

public interface NewsUserService {

    /**
     * 根据用户名，获得查询用户的方法
     *
     * @param username 要查询的用户名
     * @return 如果找到返回NewsUser对象, 找不到返回null
     */
    NewsUser findByUsername(String username);

    /**
     * 根据用户id，查询用户信息的方法
     *
     * @param userId 要查询的用户id
     * @return 找到返回NewsUser对象，找不到返回null
     */
    NewsUser findByUid(Integer userId);

    /**
     * 注册用户信息
     *
     * @param newsUser 要注册的用户信息
     * @return 注册成功返回大于0的整数，失败返回0
     */
    int registUser(NewsUser newsUser);
}
