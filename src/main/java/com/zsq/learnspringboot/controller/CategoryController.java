package com.zsq.learnspringboot.controller;


import com.zsq.learnspringboot.pojo.Category;
import com.zsq.learnspringboot.pojo.Result;
import com.zsq.learnspringboot.service.CategoryService;
import com.zsq.learnspringboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    /*
    * 新增文章分类
    * */
    @PostMapping
    public Result addCategory(@RequestBody Category category) {
        Map<String,  Object> map = ThreadLocalUtil.get();
        int userId = (Integer) map.get("id");

        category.setCreateUser(userId);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        System.out.println("添加文章分类：" + category);
        return categoryService.save(category) ? Result.success() : Result.error("添加失败");
    }

    /*
    * 文章分类列表
    * */
    @GetMapping
    public Result<List<Category>> list(){
        return Result.success(categoryService.list());
    }

    /*
    * 更新文章分类信息
    * */
    @PutMapping
    public Result updateCategory(@RequestBody Category category) {
        category.setUpdateTime(LocalDateTime.now());
        return categoryService.updateById(category) ? Result.success() : Result.error("更新失败");
    }

    /*
    * 删除文章分类
    * */
    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id) {
        return categoryService.removeById(id) ? Result.success() : Result.error("删除失败");
    }
}