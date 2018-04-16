package com.nuoxin.data.validation.common;
import com.nuoxin.data.validation.enums.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 
 * @author tiancun
 */
@ApiModel(value = "统一返回数据格式")
public class DefaultResponseBean<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6668153062059929801L;

	@ApiModelProperty(value = "返回的状态码")
	private Integer code;

	@ApiModelProperty(value = "返回的数据")
	private T data;

	@ApiModelProperty(value = "返回给前端展示的信息")
	private String codeMessage;

	@ApiModelProperty(value = "详细的描述(给开发人员看)")
	private String message;
	
	
	public static DefaultResponseBean<?> getDefaultResponseBean(Integer code, String codeMessage, String message){
		
		return new DefaultResponseBean(code, codeMessage, message);
	}
	
	
	public DefaultResponseBean() {
		this.code = ErrorEnum.SUCCESS.getCode();
		this.codeMessage = ErrorEnum.SUCCESS.getMessage();
	}
	
	public DefaultResponseBean(T data) {
		this.data = data;
		this.code = ErrorEnum.SUCCESS.getCode();
		this.codeMessage = ErrorEnum.SUCCESS.getMessage();
	}

	private DefaultResponseBean(Integer code, String codeMessage, String message) {
		this.code = code;
		this.codeMessage = codeMessage;
		this.message = message;
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}


	public String getCodeMessage() {
		return codeMessage;
	}


	public void setCodeMessage(String codeMessage) {
		this.codeMessage = codeMessage;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}

}
