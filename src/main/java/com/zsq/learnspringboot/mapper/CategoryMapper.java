package com.zsq.learnspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsq.learnspringboot.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
