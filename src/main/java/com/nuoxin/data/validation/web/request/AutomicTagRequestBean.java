package com.nuoxin.data.validation.web.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * 根据文本返回自动化标签请求类
 * @author tiancun
 * @date 2018-04-14
 */
@Data
@ApiModel(value = "根据文本返回自动化标签请求类")
public class AutomicTagRequestBean implements Serializable {


    private static final long serialVersionUID = -1251647103322892879L;

    @NonNull
    @ApiModelProperty(value = "请求的文本")
    private String text;

    public AutomicTagRequestBean(){

    }

    public AutomicTagRequestBean(String text){
        this.text = text;
    }

}
