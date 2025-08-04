package com.zsq.learnspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsq.learnspringboot.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
