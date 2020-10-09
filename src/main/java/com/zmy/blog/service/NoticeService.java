package com.zmy.blog.service;



import com.zmy.blog.entity.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeService {
    List<Notice> listNotice(Map<String, Object> criteria);
}
