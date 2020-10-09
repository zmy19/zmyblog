package com.zmy.blog.service;

import com.zmy.blog.entity.Tag;

import java.util.List;
import java.util.Map;

public interface TagService {
    List<Tag> listTag(Map<String, Object> criteria);
}
