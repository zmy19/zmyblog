package com.zmy.blog.service.impl;

import com.zmy.blog.entity.Category;
import com.zmy.blog.mapper.CategoryMapper;
import com.zmy.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zengmy
 * @description:
 * @date 2020-10-09
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listCategory() {
        List<Category> categoryList = categoryMapper.selectByExample(null);
        return categoryList;
    }
}
