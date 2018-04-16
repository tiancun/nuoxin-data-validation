package com.nuoxin.data.validation.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 返回的不同类型的标签集合，目前有四类
 * @author tiancun
 * @date 2018-04-14
 */
@Data
@ApiModel(value = "返回的不同类型的标签集合，目前有四类")
public class AutomicTagListResponseBean implements Serializable {
    private static final long serialVersionUID = -3289804089625973714L;


    @ApiModelProperty(value = "辉瑞产品标签")
    private List<AutomicTagResponseBean> cateA = new ArrayList<>();

    @ApiModelProperty(value = "非辉瑞产品标签")
    private List<AutomicTagResponseBean> cateB = new ArrayList<>();

    @ApiModelProperty(value = "疾病症状加上诊断词汇")
    private List<AutomicTagResponseBean> cateC = new ArrayList<>();

    @ApiModelProperty(value = "其他标签")
    private List<AutomicTagResponseBean> cateD = new ArrayList<>();



}
