package com.zmy.blog.service.impl;

import com.zmy.blog.entity.Comment;
import com.zmy.blog.mapper.CommentMapper;
import com.zmy.blog.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public List<Comment> listRecnetComment(Map map) {
        return commentMapper.getRecentComment(map);
    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        List<Comment> commentList = null;
        try {
            commentList = commentMapper.listCommentByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据文章ID获得评论列表失败，articleId:{},cause:{}", articleId, e);
        }
        return commentList;
    }
}
