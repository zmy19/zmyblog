package com.zmy.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.blog.entity.Article;
import com.zmy.blog.entity.ArticleExample;
import com.zmy.blog.entity.Category;
import com.zmy.blog.entity.UserExample;
import com.zmy.blog.mapper.ArticleCategoryRefMapper;
import com.zmy.blog.mapper.ArticleMapper;
import com.zmy.blog.mapper.CategoryMapper;
import com.zmy.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zengmy
 * @description:
 * @date 2020-09-27
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    /**
     * @Author zengmy
     * @Despcription 获取最新发布的文章
     * @Date 2020/9/27 15:22
     * @param * @param null
     * @return
     **/
    @Override
    public List<Article> listRecentArticle(Map map) {
        return articleMapper.getListRecentArticle(map);
    }

    /**
     * @Author zengmy
     * @Despcription 获取文章分页列表
     * @Date 2020/9/27 16:49
     * @param * @param null
     * @return
     **/
    @Override
    public PageInfo<Article> pagelistArticle(Integer pageIndex, Integer pageSize,Map criteria) {
        //紧跟在后面的查询会被分页
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria1 = example.createCriteria();
        Object articleStatus = criteria.get("articleStatus");
        if (articleStatus != null && articleStatus instanceof Integer) {
            criteria1.andArticleStatusEqualTo((Integer)articleStatus);
        }
        PageHelper.startPage(pageIndex,pageSize);
        List<Article> articleList = articleMapper.selectByExample(example);
        if (articleList != null && articleList.size() > 0) {
            for (Article article : articleList) {
                List<Category> categoryList = articleCategoryRefMapper.selectCategoryByArticleId(article.getArticleId());
                if (categoryList == null || categoryList.size() == 0) {
                    categoryList = new ArrayList<>();
                    categoryList.add(Category.Default());
                }
                article.setCategoryList(categoryList);
            }
        }
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }
}
