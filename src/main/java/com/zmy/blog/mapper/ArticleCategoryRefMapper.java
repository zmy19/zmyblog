package com.zmy.blog.mapper;

import com.zmy.blog.entity.ArticleCategoryRef;
import com.zmy.blog.entity.ArticleCategoryRefExample;
import java.util.List;

import com.zmy.blog.entity.Category;
import org.apache.ibatis.annotations.Param;

public interface ArticleCategoryRefMapper {
    long countByExample(ArticleCategoryRefExample example);

    int deleteByExample(ArticleCategoryRefExample example);

    int insert(ArticleCategoryRef record);

    int insertSelective(ArticleCategoryRef record);

    List<ArticleCategoryRef> selectByExample(ArticleCategoryRefExample example);

    int updateByExampleSelective(@Param("record") ArticleCategoryRef record, @Param("example") ArticleCategoryRefExample example);

    int updateByExample(@Param("record") ArticleCategoryRef record, @Param("example") ArticleCategoryRefExample example);

    List<Category> selectCategoryByArticleId(@Param("aid") Integer articleId);
}