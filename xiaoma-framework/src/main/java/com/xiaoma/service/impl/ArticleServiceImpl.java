package com.xiaoma.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoma.constant.SystemConstants;
import com.xiaoma.domain.entity.Article;
import com.xiaoma.domain.entity.Category;
import com.xiaoma.mapper.ArticleMapper;
import com.xiaoma.service.ArticleService;
import com.xiaoma.service.CategoryService;
import com.xiaoma.utils.BeanCopyUtils;
import com.xiaoma.vo.ArticleDetailVo;
import com.xiaoma.vo.HotArticleVo;
import com.xiaoma.vo.PageVo;
import com.xiaoma.vo.ResponseResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    CategoryService categoryService;

    @Autowired(required = false)
    ArticleMapper articleMapper;
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        //前端不需要返回那么多字段，需要对前端返回对象简化
        //使用bean拷贝
        List<Article> articles = page.getRecords();

        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article, vo);
//            articleVos.add(vo);
//        }
        return ResponseResult.okResult(articleVos);
    }

    @Override
    public ResponseResult articleList(Long categoryId, Integer pageNum, Integer pageSize) {

//        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();
//
//        if (categoryId != null) {
//            queryWrapper.eq(Article::getCategoryId, categoryId);
//        }
//
//        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
//
//        queryWrapper.orderByDesc(Article::getViewCount);
//        Page<Article> page = new Page<>(1, 10);
//        page(page, queryWrapper);
//        List<Article> articles = page.getRecords();
//        long total = page.getTotal();

//        for(Article article : articles){
//            article.setCategoryName();
//        }
        List<Article> categoryArticle = null;
        if(categoryId != null){
            categoryArticle = articleMapper.findCategoryArticle(categoryId, pageNum, pageSize);
        }else{
            categoryArticle = articleMapper.findAllArticle(pageNum, pageSize);
        }
        PageVo pageVo = new PageVo(categoryArticle, (long) categoryArticle.size());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
//        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Article::getId, id);
//        Article article = articleMapper.selectById(id);
        Article article = getById(id);
        Category category = categoryService.getById(article.getCategoryId());
        if(category != null){
            article.setCategoryName(category.getName());
        }
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        return ResponseResult.okResult(articleDetailVo);
    }



}
