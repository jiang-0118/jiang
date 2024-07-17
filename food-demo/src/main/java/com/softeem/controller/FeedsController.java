package com.softeem.controller;

import com.softeem.service.IFeedsService;
import com.softeem.util.ResultInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feeds")
public class FeedsController {

    @Resource
    private IFeedsService feedsService;
    @PostMapping("/create")
    public ResultInfo create(String content){
        return feedsService.create(content);
    }
}
