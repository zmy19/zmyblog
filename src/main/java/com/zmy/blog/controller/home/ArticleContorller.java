package com.zmy.blog.controller.home;

import com.zmy.blog.entity.*;
import com.zmy.blog.enums.ArticleStatus;
import com.zmy.blog.service.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: zengmy
 * @description: 前台文章等显示
 * @date 2020-09-27
 */

@Controller
public class ArticleContorller {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    /**
     * @Author zengmy
     * @Despcription 文章详情页显示
     * @Date 2020/10/9 16:19
     * @param * @param articleId 文章ID
     * @return
     **/
    @RequestMapping(value = "/article/{articleId}")
    public String articleDetailPage(@PathVariable("articleId") Integer articleId, Model model) {

        //文章信息，分类，标签，作者，评论
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),articleId);
        if (article == null) {
            return "Home/Error/404";
        }

        //用户信息
        User user = userService.getUserById(article.getArticleUserId());
        article.setUser(user);

        //文章信息
        model.addAttribute("article",article);

        //评论列表
        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList",commentList);

        //相关文章
        List<Integer> categoryIds = articleService.listCategoryIdByArticleId(articleId);
        List<Article> similarArticleList = articleService.listArticleByCategoryIds(categoryIds,5);
        model.addAttribute("similarArticleList",similarArticleList);

        //猜你喜欢
        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
        model.addAttribute("mostViewArticleList",mostViewArticleList);

        //获取下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId);
        model.addAttribute("afterArticle",afterArticle);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId);
        model.addAttribute("preArticle",preArticle);

        //侧边栏
        //标签列表显示
        List<Tag> allTagList = tagService.listTag(null);
        model.addAttribute("allTagList",allTagList);

        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList",randomArticleList);

        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);

        return "Home/Page/articleDetail";
    }
}
