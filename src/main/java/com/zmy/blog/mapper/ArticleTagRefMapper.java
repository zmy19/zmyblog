package com.zmy.blog.mapper;

import com.zmy.blog.entity.ArticleTagRefExample;
import com.zmy.blog.entity.ArticleTagRefKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleTagRefMapper {
    long countByExample(ArticleTagRefExample example);

    int deleteByExample(ArticleTagRefExample example);

    int deleteByPrimaryKey(ArticleTagRefKey key);

    int insert(ArticleTagRefKey record);

    int insertSelective(ArticleTagRefKey record);

    List<ArticleTagRefKey> selectByExample(ArticleTagRefExample example);

    int updateByExampleSelective(@Param("record") ArticleTagRefKey record, @Param("example") ArticleTagRefExample example);

    int updateByExample(@Param("record") ArticleTagRefKey record, @Param("example") ArticleTagRefExample example);
}