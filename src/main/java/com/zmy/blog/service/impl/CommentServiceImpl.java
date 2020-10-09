package com.zmy.blog.service.impl;

import com.zmy.blog.entity.Comment;
import com.zmy.blog.mapper.CommentMapper;
import com.zmy.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zengmy
 * @description:
 * @date 2020-09-27
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> listRecnetComment(Map map) {
        return commentMapper.getRecentComment(map);
    }
}
