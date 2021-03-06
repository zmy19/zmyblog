package com.zmy.blog.mapper;

import com.zmy.blog.entity.Article;
import com.zmy.blog.entity.ArticleExample;
import com.zmy.blog.entity.ArticleWithBLOBs;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {
    long countByExample(ArticleExample example);

    int deleteByExample(ArticleExample example);

    int deleteByPrimaryKey(Integer articleId);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    List<ArticleWithBLOBs> selectByExampleWithBLOBs(ArticleExample example);

    List<Article> selectByExample(ArticleExample example);

    ArticleWithBLOBs selectByPrimaryKey(Integer articleId);

    int updateByExampleSelective(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExampleWithBLOBs(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

    int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

    List<Article> getListRecentArticle(@Param("map") Map map);

    List<Integer> listCategoryIdByArticleId(@Param("id") Integer articleId);

    List<Article> listArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                           @Param("limit") Integer limit);

    List<Article> listArticleByViewCount(@Param("limit") Integer limit);

    Article getAfterArticle(@Param("id") Integer articleId);

    Article getPreArticle(@Param("id") Integer articleId);

    List<Article> listRandomArticle(@Param("limit") Integer limit);

    List<Article> listArticleByCommentCount(@Param("limit") Integer limit);
}