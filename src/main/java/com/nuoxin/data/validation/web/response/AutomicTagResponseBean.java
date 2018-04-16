package com.nuoxin.data.validation.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 根据文本内容返回的标签
 * @author tiancun
 * @date 2018-04-14
 */
@Data
@ApiModel(value = "根据文本返回的标签")
public class AutomicTagResponseBean implements Serializable {
    private static final long serialVersionUID = 4050429237958892734L;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "标签出现的次数")
    private Integer count;

    @ApiModelProperty(value = "tfIdf值")
    private String tfIdf;


}
