package com.zmy.blog.service.impl;

import com.zmy.blog.entity.Tag;
import com.zmy.blog.mapper.TagMapper;
import com.zmy.blog.service.TagService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zengmy
 * @description: 标签列表的业务类
 * @date 2020-09-27
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;


    @Override
    public List<Tag> listTag(Map<String, Object> criteria) {
        return tagMapper.selectByExample(null);
    }
}
