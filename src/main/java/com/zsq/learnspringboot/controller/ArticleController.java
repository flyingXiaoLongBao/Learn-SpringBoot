package com.zsq.learnspringboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsq.learnspringboot.pojo.Article;
import com.zsq.learnspringboot.pojo.DTO.PageQuery;
import com.zsq.learnspringboot.pojo.Result;
import com.zsq.learnspringboot.service.ArticleService;
import com.zsq.learnspringboot.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /*
     * 新增文章
     * */
    @PostMapping
    public Result addArticle(@RequestBody Article article) {
        Map<String, Object> map = ThreadLocalUtil.get();
        int userId = (Integer) map.get("id");

        article.setCreateUser(userId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        return articleService.save(article) ? Result.success() : Result.error("添加失败");
    }
    /*
    * 更新文章
    * */
    @PutMapping
    public Result updateArticle(@RequestBody Article article) {
        article.setUpdateTime(LocalDateTime.now());
        return articleService.save( article)? Result.success() : Result.error("更新失败");
    }

    /*
    * 获取文章详情
    * */
    @GetMapping("/detail")
    public Result<Article> getArticleDetail(Integer id) {
        Article article = articleService.getById(id);
        return Result.success(article);
    }

    /*
    * 删除文章
    * */
    @DeleteMapping
    public Result deleteArticle(Integer id) {
        return articleService.removeById(id) ? Result.success() : Result.error("删除失败");
    }

    /*
     * 文章列表(条件分页)
     * */
    @GetMapping
    public Result<IPage<Article>> pageByCondition(PageQuery pageQuery) {
        Page<Article> page = articleService.pageByCondition(pageQuery);
        return Result.success(page);
    }
}
