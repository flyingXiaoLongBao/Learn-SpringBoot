package com.zsq.learnspringboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsq.learnspringboot.mapper.CategoryMapper;
import com.zsq.learnspringboot.pojo.Category;
import com.zsq.learnspringboot.service.CategoryService;
import org.springframework.stereotype.Service;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
