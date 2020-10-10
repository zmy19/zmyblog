package com.zmy.blog.service;

import com.github.pagehelper.PageInfo;
import com.zmy.blog.entity.Article;
import com.zmy.blog.enums.ArticleStatus;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    List<Article> listRecentArticle(Map map);

    PageInfo<Article> pagelistArticle(Integer pageIndex, Integer pageSize,Map criteria);

    Article getArticleByStatusAndId(Integer value, Integer articleId);

    List<Integer> listCategoryIdByArticleId(Integer articleId);

    List<Article> listArticleByCategoryIds(List<Integer> categoryIds,Integer limit);

    List<Article> listArticleByViewCount(Integer limit);

    Article getAfterArticle(Integer articleId);

    Article getPreArticle(Integer articleId);

    List<Article> listRandomArticle(Integer limit);

    List<Article> listArticleByCommentCount(Integer limit);
}
