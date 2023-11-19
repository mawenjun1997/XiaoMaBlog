package com.xiaoma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoma.domain.entity.Category;
import com.xiaoma.vo.ResponseResult;


/**
 * 分类表(Catagory)表服务接口
 *
 * @author makejava
 * @since 2023-10-23 00:09:44
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

