package com.xiaoma.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoma.domain.entity.Link;
import com.xiaoma.vo.ResponseResult;
import org.springframework.stereotype.Service;


public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
