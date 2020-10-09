package com.zmy.blog.service;

import com.github.pagehelper.PageInfo;
import com.zmy.blog.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<Article> listRecentArticle(Map map);

    PageInfo<Article> pagelistArticle(Integer pageIndex, Integer pageSize,Map criteria);
}
