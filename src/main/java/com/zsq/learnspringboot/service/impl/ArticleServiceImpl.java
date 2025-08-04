package com.zsq.learnspringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsq.learnspringboot.mapper.ArticleMapper;
import com.zsq.learnspringboot.pojo.Article;
import com.zsq.learnspringboot.pojo.DTO.PageQuery;
import com.zsq.learnspringboot.service.ArticleService;
import org.springframework.stereotype.Service;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public Page<Article> pageByCondition(PageQuery pageQuery){
        //设置查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(pageQuery.getCategoryId() != null, Article::getCategoryId, pageQuery.getCategoryId())
                .eq( pageQuery.getState() != null, Article::getState, pageQuery.getState());
        //设置分页参数
        Page<Article> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        //查询
        return this.page(page, queryWrapper);
    }
}
