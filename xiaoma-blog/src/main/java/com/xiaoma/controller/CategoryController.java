package com.xiaoma.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoma.domain.entity.Category;
import com.xiaoma.service.CategoryService;
import com.xiaoma.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@ResponseBody
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/getCategoryList")
    public ResponseResult getCategoryList(){

        ResponseResult responseResult = categoryService.getCategoryList();

        return responseResult;
    }
}

