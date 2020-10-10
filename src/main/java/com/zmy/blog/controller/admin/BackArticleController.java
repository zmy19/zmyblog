package com.zmy.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.zmy.blog.entity.Article;
import com.zmy.blog.entity.Category;
import com.zmy.blog.entity.Tag;
import com.zmy.blog.service.ArticleService;
import com.zmy.blog.service.CategoryService;
import com.zmy.blog.service.TagService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zengmy
 * @description: 后台文章控制类
 * @date 2020-10-09
 */
@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;


    /**
     * @Author zengmy
     * @Despcription 后台文章列表显示
     * @Date 2020/10/9 15:44
     * @param * @param null
     * @return modelAndView
     **/
    @RequestMapping(value = "")
    public String index(@RequestParam(required = false,defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                        @RequestParam(required = false) String status, Model model) {
        HashMap<String,Object> criteria = new HashMap<>(1);
        if (status == null) {
            model.addAttribute("pageUrlPrefix","/admin/article?pageIndex");
        } else {
            criteria.put("status",status);
            model.addAttribute("pageUrlPrefix","/admin/article?status="+status+"&pageIndex");
        }
        PageInfo<Article> articlePageInfo = articleService.pagelistArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articlePageInfo);
        return "Admin/Article/index";
    }

    /**
     * @Author zengmy
     * @Despcription 跳转到添加文章界面
     * @Date 2020/10/10
     * @param * @param null
     * @return
     **/
    @RequestMapping(value = "/insert")
    public String insertArticle(Model model) {
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTag(null);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        return "Admin/Article/insert";
    }

    /**
     * @Author zengmy
     * @Despcription 编辑文章页面显示
     * @Date 2020/10/10 16:47
     * @param * @param null
     * @return
     **/
    @RequestMapping(value = "/edit/{articleId}")
    public ModelAndView editArticle(@PathVariable("articleId") Integer id,ModelAndView modelAndView) {
        //根据文章id查询对应的taglist和catetorylist
        Article article = articleService.getArticleByStatusAndId(null, id);
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.listTag(null);
        modelAndView.addObject("article",article);
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.addObject("tagList",tagList);
        modelAndView.setViewName("Admin/Article/edit");
        return  modelAndView;
    }
}
