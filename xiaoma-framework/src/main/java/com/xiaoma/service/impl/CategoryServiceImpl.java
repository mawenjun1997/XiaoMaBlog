package com.xiaoma.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoma.constant.SystemConstants;
import com.xiaoma.domain.entity.Article;
import com.xiaoma.domain.entity.Category;
import com.xiaoma.mapper.CategoryMapper;
import com.xiaoma.service.ArticleService;
import com.xiaoma.utils.BeanCopyUtils;
import com.xiaoma.vo.CategoryVo;
import com.xiaoma.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoma.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分类表(Catagory)表服务实现类
 *
 * @author makejava
 * @since 2023-10-23 00:09:45
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    ArticleService articleService;
    @Override
    public ResponseResult getCategoryList() {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        List<Article> articleList = articleService.list(queryWrapper);

        List<Long> categoryids = articleList.stream().map(Article::getCategoryId).collect(Collectors.toList());

        List<Category> categories = listByIds(categoryids);

        List<Category> categoryList = categories.stream().filter(category -> SystemConstants.CATEGORY_STATUS_NORMAL.equals(category.getStatus())).collect(Collectors.toList());

        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);

    }
}

