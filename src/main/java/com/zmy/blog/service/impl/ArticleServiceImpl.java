package com.zmy.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zmy.blog.entity.*;
import com.zmy.blog.enums.ArticleStatus;
import com.zmy.blog.mapper.*;
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

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public Article getArticleByStatusAndId(Integer value, Integer articleId) {
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        if (value != null) {
            criteria.andArticleStatusEqualTo(value);
        }
        criteria.andArticleIdEqualTo(articleId);
        List<Article> articleList = articleMapper.selectByExample(example);
        Article article = new Article();
        if (articleList != null && articleList.size() > 0) {
            article = articleList.get(0);
        }
        if (article != null) {
            User user = userMapper.selectByPrimaryKey(article.getArticleUserId());
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
            List<Tag> tagList =  tagMapper.listTagbyArticleId(article.getArticleId());
            article.setCategoryList(categoryList);
            article.setTagList(tagList);
            article.setUser(user);
        }
        return article;
    }

    @Override
    public List<Integer> listCategoryIdByArticleId(Integer articleId) {
        List<Integer> categoryIds = articleMapper.listCategoryIdByArticleId(articleId);
        return categoryIds;
    }

    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> categoryIds,Integer limit) {
        List<Article> articleList = articleMapper.listArticleByCategoryIds(categoryIds,limit);
        return articleList;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        List<Article> articleList = articleMapper.listArticleByViewCount(limit);
        return articleList;
    }

    @Override
    public Article getAfterArticle(Integer articleId) {
        Article article = articleMapper.getAfterArticle(articleId);
        return article;
    }

    @Override
    public Article getPreArticle(Integer articleId) {
        Article article = articleMapper.getPreArticle(articleId);
        return article;
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        List<Article> randomArticleList = articleMapper.listRandomArticle(limit);
        return randomArticleList;
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        List<Article> hotArticleList = articleMapper.listArticleByCommentCount(limit);
        return hotArticleList;
    }

}
