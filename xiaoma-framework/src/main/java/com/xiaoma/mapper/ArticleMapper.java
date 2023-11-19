package com.xiaoma.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoma.domain.entity.Article;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> findCategoryArticle(@Param("categoryId") Long categoryId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    List<Article> findAllArticle(@Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize);

}
