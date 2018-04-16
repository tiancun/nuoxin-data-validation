package com.nuoxin.data.validation.web.controller;

import com.nuoxin.data.validation.common.DefaultResponseBean;
import com.nuoxin.data.validation.service.DoctorRecommendService;
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
 * 医生推荐相关接口
 * @author tiancun
 * @date 2018-04-13
 */
@Api(value = "医生推荐相关接口")
@RestController
@RequestMapping(value = "/doctor/recommend")
public class DoctorRecommendController {

    @Autowired
    private DoctorRecommendService doctorRecommendService;

    @ApiOperation(value = "导入excel返回医生推荐列表", notes = "导入excel返回医生推荐列表")
    @PostMapping(value = "/import")
    public DefaultResponseBean<List<RecommendListBean>> importExcel(MultipartFile file){

        List<RecommendListBean> recommendList = doctorRecommendService.getRecommendList(file);

        DefaultResponseBean<List<RecommendListBean>> defaultResponseBean = new DefaultResponseBean<>(recommendList);
        return defaultResponseBean;
    }

}
