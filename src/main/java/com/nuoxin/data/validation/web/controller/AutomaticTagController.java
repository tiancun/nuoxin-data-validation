package com.nuoxin.data.validation.web.controller;

import com.nuoxin.data.validation.common.DefaultResponseBean;
import com.nuoxin.data.validation.service.AutomaticTagService;
import com.nuoxin.data.validation.web.response.AutomicTagListResponseBean;
import com.nuoxin.data.validation.web.response.RecommendListBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 自动化标签相关接口
 * @author tiancun
 * @date 2018-04-13
 */
@Api(value = "自动化标签相关接口")
@RestController
@RequestMapping(value = "/automatic/tag")
public class AutomaticTagController {

    @Autowired
    private AutomaticTagService automaticTagService;

    @ApiOperation(value = "导入office文件返回计算好的标签", notes = "导入office文件返回计算好的标签")
    @PostMapping(value = "/import")
    public DefaultResponseBean<AutomicTagListResponseBean> importExcel(MultipartFile file){

        AutomicTagListResponseBean tags = automaticTagService.getTags(file);
        DefaultResponseBean<AutomicTagListResponseBean> responseBean = new DefaultResponseBean<>(tags);
        return responseBean;
    }

}
