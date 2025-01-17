package com.atiguigu.topnews.service;

import com.atiguigu.topnews.pojo.NewsUser;

public interface NewsUserService {

    /**
     * 根据用户名,获得查询用户的方法
     *
     * @param username 要查询的用户名
     * @return 如果找到返回NewsUser对象, 找不到返回null
     */
    NewsUser findByUsername(String username);
}
