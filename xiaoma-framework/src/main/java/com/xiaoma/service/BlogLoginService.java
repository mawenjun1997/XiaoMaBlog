package com.xiaoma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoma.domain.entity.User;
import com.xiaoma.vo.ResponseResult;

public interface BlogLoginService{
    ResponseResult login(User user);
}
