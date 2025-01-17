package com.atiguigu.topnews.dao.impl;

import com.atiguigu.topnews.dao.BaseDao;
import com.atiguigu.topnews.dao.NewsHeadlineDao;
import com.atiguigu.topnews.pojo.HeadLinePageVo;
import com.atiguigu.topnews.pojo.HeadLineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadLineDaoImpl extends BaseDao implements NewsHeadlineDao {
    @Override
    public List<HeadLinePageVo> findPageList(HeadLineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = "SELECT `hid`, `title`, `type`, `page_views` AS `pageViews`, TIMESTAMPDIFF(HOUR,`create_time`,NOW()) AS `pastHours`, `publisher` FROM `news_headline` WHERE `is_deleted` = 0";
        StringBuilder sqlBuilder = new StringBuilder(sql);

        Integer type = headlineQueryVo.getType();
        if (type != 0) {
            sqlBuilder.append(" AND `type` = ? ");
            params.add(type);
        }

        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().isEmpty()) {
            sqlBuilder.append(" AND `title` LIKE ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }

        sqlBuilder.append(" ORDER BY `pastHours` ASC, `page_views` DESC ");
        sqlBuilder.append(" LIMIT ?,?");

        params.add((headlineQueryVo.getPageNum() - 1) * headlineQueryVo.getPageSize());
        params.add(headlineQueryVo.getPageSize());

        sql = sqlBuilder.toString();
        return baseQuery(HeadLinePageVo.class, sql, params.toArray());
    }

    @Override
    public int findPageCount(HeadLineQueryVo headlineQueryVo) {
        List params = new ArrayList();
        String sql = "SELECT COUNT(1) FROM `news_headline` WHERE `is_deleted` = 0";
        StringBuilder sqlBuilder = new StringBuilder(sql);

        Integer type = headlineQueryVo.getType();
        if (type != 0) {
            sqlBuilder.append(" AND `type` = ? ");
            params.add(type);
        }

        if (headlineQueryVo.getKeyWords() != null && !headlineQueryVo.getKeyWords().isEmpty()) {
            sqlBuilder.append(" AND `title` LIKE ? ");
            params.add("%" + headlineQueryVo.getKeyWords() + "%");
        }

        sql = sqlBuilder.toString();
        return baseQueryObject(Long.class, sql, params.toArray()).intValue();
    }
}
