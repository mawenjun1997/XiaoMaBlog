package com.xiaoma.controller;

import com.xiaoma.service.LinkService;
import com.xiaoma.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @GetMapping("getAllLink")
    public ResponseResult getAllLink(){
        return linkService.getAllLink();
    }

}
