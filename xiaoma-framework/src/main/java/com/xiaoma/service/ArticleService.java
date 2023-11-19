package com.xiaoma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoma.domain.entity.Article;
import com.xiaoma.vo.ResponseResult;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Long categoryId, Integer pagNum, Integer pageSize);

    ResponseResult getArticleDetail(Long id);
}
