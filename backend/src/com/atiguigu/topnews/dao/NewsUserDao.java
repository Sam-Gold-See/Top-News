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
}
