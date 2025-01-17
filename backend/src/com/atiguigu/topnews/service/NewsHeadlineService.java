package com.atiguigu.topnews.service;

import com.atiguigu.topnews.pojo.HeadLineQueryVo;

import java.util.Map;

public interface NewsHeadLineService {
    /**
     * 分页查询头条新闻的业务接口实现
     *
     * @param headlineQueryVo 分页查询的五项数据对象
     * @return 返回pageData的键值对查询结果对象
     */
    Map<String, Object> findPage(HeadLineQueryVo headlineQueryVo);
}
