package com.atiguigu.topnews.dao.impl;

import com.atiguigu.topnews.dao.BaseDao;
import com.atiguigu.topnews.dao.NewsHeadlineDao;
import com.atiguigu.topnews.pojo.HeadLineDetailVo;
import com.atiguigu.topnews.pojo.HeadLinePageVo;
import com.atiguigu.topnews.pojo.HeadLineQueryVo;
import com.atiguigu.topnews.pojo.NewsHeadLine;

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

    @Override
    public void increasePageViews(Integer hid) {
        String sql = "UPDATE `news_headline` set `page_views` = `page_views` + 1 WHERE `hid` = ?";
        baseUpdate(sql, hid);
    }

    @Override
    public HeadLineDetailVo findHeadlineDetail(Integer hid) {
        String sql = "SELECT `hid`, `title`, `article`, `type`, `tname` AS `typeName`, `page_views` AS `pageViews`, TIMESTAMPDIFF(HOUR,`create_time`,NOW()) `pastHours`, `publisher`, `nick_name` AS `author` FROM `news_headline` h left join `news_type` t on h.type = t.tid left join news_user u  on h.publisher = u.uid where `hid` = ?";
        List<HeadLineDetailVo> headLineDetailVoList = baseQuery(HeadLineDetailVo.class, sql, hid);
        if (null != headLineDetailVoList && !headLineDetailVoList.isEmpty())
            return headLineDetailVoList.get(0);
        return null;
    }

    @Override
    public int addNewsHeadline(NewsHeadLine newsHeadLine) {
        String sql = "INSERT INTO `news_headline` VALUES (DEFAULT,?,?,?,?,0,NOW(),NOW(),0)";
        return baseUpdate(sql, newsHeadLine.getTitle(), newsHeadLine.getArticle(), newsHeadLine.getType(), newsHeadLine.getPublisher());
    }

    @Override
    public NewsHeadLine findHeadlineByHid(Integer hid) {
        String sql = "SELECT `hid`, `title`, `article`, `type`, `publisher`, `page_views` AS `pageViews` FROM `news_headline` WHERE `hid` = ?";
        List<NewsHeadLine> newsHeadLineList = baseQuery(NewsHeadLine.class, sql, hid);
        if (null != newsHeadLineList && !newsHeadLineList.isEmpty())
            return newsHeadLineList.get(0);
        return null;
    }
}
