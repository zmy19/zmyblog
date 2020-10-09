package com.zmy.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.zmy.blog.entity.*;
import com.zmy.blog.enums.ArticleStatus;
import com.zmy.blog.enums.LinkStatus;
import com.zmy.blog.enums.NoticeStatus;
import com.zmy.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zengmy
 * @description: 前台页面的相关控制，用户的controller
 * @date 2020-09-27
 */

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    /**
     * @Author zengmy
     * @Despcription 将前台页面的文章，公告，友情链接，侧边栏，最新评论等查询，返回前台
     * @Date 2020/9/27 16:35
     * @param * @param null
     * @return
     **/
    @RequestMapping(value = {"/","/article"})
    public String index(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10") Integer pageSize, Model model) {
        Map<String,Object> criteria = new HashMap<>();
        //文章为已发布状态
        criteria.put("articleStatus", ArticleStatus.PUBLISH.getValue());
        //文章列表
        PageInfo<Article> articleList = articleService.pagelistArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articleList);
        //公告
        criteria.put("noticeStatus", NoticeStatus.NORMAL.getValue());
        List<Notice> noticeList = noticeService.listNotice(criteria);
        model.addAttribute("noticeList",noticeList);
        //友情链接
        criteria.put("linkStatus", LinkStatus.NORMAL.getValue());
        List<Link> linkList = linkService.listLink(criteria);
        model.addAttribute("linkList",linkList);
        //侧边栏显示
        //标签列表显示
        List<Tag> tagList = tagService.listTag(criteria);
        model.addAttribute("allTagList",tagList);
        //最新评论
        List<Comment> commentList = commentService.listRecnetComment(criteria);
        model.addAttribute("commentList",commentList);
        return "Home/index";
    }
}
