package com.xiaoma.controller;

import com.xiaoma.domain.entity.Article;
import com.xiaoma.service.ArticleService;
import com.xiaoma.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@ResponseBody
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){

        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    public ResponseResult articleList( Long categoryId, Integer pageNum, Integer pageSize){

        ResponseResult responseResult = articleService.articleList(categoryId, pageNum, pageSize);
        return responseResult;
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        ResponseResult responseResult = articleService.getArticleDetail(id);
        return responseResult;
    }




}
