package com.zmy.blog.service.impl;

import com.zmy.blog.entity.Link;
import com.zmy.blog.entity.LinkExample;
import com.zmy.blog.mapper.LinkMapper;
import com.zmy.blog.service.LinkService;
import jdk.internal.dynalink.support.LinkerServicesImpl;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zengmy
 * @description: 链接业务类
 * @date 2020-09-27
 */

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    /**
     * @Author zengmy
     * @Despcription 获取链接列表
     * @Date 2020/9/27 17:20
     * @param * @param null
     * @return
     **/
    @Override
    public List<Link> listLink(Map<String, Object> criteria) {
        LinkExample example = new LinkExample();
        LinkExample.Criteria criteria1 = example.createCriteria();
        criteria1.andLinkStatusEqualTo((Integer) criteria.get("linkStatus"));
        List<Link> linkList = linkMapper.selectByExample(example);
        return linkList;
    }
}
