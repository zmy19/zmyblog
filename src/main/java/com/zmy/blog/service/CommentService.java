package com.zmy.blog.service;

import com.zmy.blog.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<Comment> listRecnetComment(Map map);
}
