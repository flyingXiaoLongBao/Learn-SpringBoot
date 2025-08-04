package com.zsq.learnspringboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsq.learnspringboot.pojo.Article;
import com.zsq.learnspringboot.pojo.DTO.PageQuery;

public interface ArticleService extends IService<Article> {
    Page<Article> pageByCondition(PageQuery pageQuery);
}
