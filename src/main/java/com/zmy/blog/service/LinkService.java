package com.zmy.blog.service;

import com.zmy.blog.entity.Link;

import java.util.List;
import java.util.Map;

public interface LinkService {

    List<Link> listLink(Map<String, Object> criteria);
}
