package com.zmy.blog.service.impl;

import com.zmy.blog.entity.Notice;
import com.zmy.blog.entity.NoticeExample;
import com.zmy.blog.mapper.NoticeMapper;
import com.zmy.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zengmy
 * @description: 关于通知notice的业务类
 * @date 2020-09-27
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> listNotice(Map<String, Object> criteria) {
        NoticeExample example = new NoticeExample();
        NoticeExample.Criteria criteria1 = example.createCriteria();
        criteria1.andNoticeStatusEqualTo((Integer) criteria.get("noticeStatus"));
        List<Notice> noticeList = noticeMapper.selectByExample(example);
        return noticeList;
    }
}
