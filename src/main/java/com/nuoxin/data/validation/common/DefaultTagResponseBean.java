package com.nuoxin.data.validation.common;

import com.nuoxin.data.validation.web.response.AutomicTagListResponseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

/**
 * 调用python 接口请求文章内容分词标签，返回同一个格式数据
 * @author tiancun
 * @date 2018-04-14
 */
@Data
@ApiModel(value = "调用python 接口请求文章内容分词标签，返回同一个格式数据")
public class DefaultTagResponseBean {

    @ApiModelProperty(value = "状态码，现在只有200成功，500失败")
    private Integer code;

    @ApiModelProperty(value = "返回数据")
    private AutomicTagListResponseBean data;

    @ApiModelProperty(value = "请求的错误信息，供前端展示")
    private String message;

    @ApiModelProperty(value = "错误的详细信息")
    private String description;

}
