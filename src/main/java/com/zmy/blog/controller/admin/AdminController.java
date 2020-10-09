package com.zmy.blog.controller.admin;

import com.zmy.blog.entity.Article;
import com.zmy.blog.entity.Comment;
import com.zmy.blog.entity.User;
import com.zmy.blog.service.ArticleService;
import com.zmy.blog.service.CommentService;
import com.zmy.blog.service.UserService;
import com.zmy.blog.util.MyUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zengmy
 * @description: 后台的登录等操作
 * @date 2020-09-22
 */
@Controller
public class AdminController {

    @Autowired
    public UserService userService;
    @Autowired
    public ArticleService articleService;
    @Autowired
    public CommentService commentService;
    /**
     * @Author zengmy
     * @Despcription 跳转到登陆页
     * @Date 2020/9/22 10:11
     * @param * @param null
     * @return
     **/
    @RequestMapping(value = {"/login"})
    public String loginPage() {
        return "Admin/login";
    }

    /**
     * @Author zengmy
     * @Despcription 后台首页
     * @Date 2020/9/27 14:50
     * @param * @param null
     * @return
     **/
    @RequestMapping("/admin")
    public String index(Model model) {
        //文章列表
        Map<String,Object> map = new HashMap<>();
        map.put("limit",5);
        //获取近期的文章
        List<Article> articleList = articleService.listRecentArticle(map);
        model.addAttribute("articleList",articleList);
        //获取近期的评论
        List<Comment> commentList = commentService.listRecnetComment(map);
        model.addAttribute("commentList",commentList);
        return "/Admin/index";
    }

    /**
     * @Author zengmy
     * @Despcription 登录验证
     * @Date 2020/9/22 18:07
     * @param * @param null
     * @return
     **/
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getUserByNameOrEmail(username);
        if (user == null) {
            map.put("code",0);
            map.put("msg","用户名无效！");
        } else if (!user.getUserPass().equals(password)) {
            map.put("code",0);
            map.put("msg","密码错误");
        } else {
            //登录成功
            map.put("code",1);
            map.put("msg","");
            //添加session
            request.getSession().setAttribute("user",user);
            //添加cookie
            if (rememberme != null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username",username);
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password",password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(MyUtils.getInAddr(request,response));
            userService.updateUser(user);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * @Author zengmy
     * @Despcription 退出登录
     * @Date 2020/9/27 16:05
     * @param * @param null
     * @return
     **/
    @RequestMapping(value = "/admin/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }
}
