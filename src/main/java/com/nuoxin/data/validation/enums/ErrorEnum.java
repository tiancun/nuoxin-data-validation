package com.nuoxin.data.validation.enums;

/**
 * 统一错误说明
 * @author tiancun
 *
 */
public enum ErrorEnum {

	SUCCESS(200, "请求成功"),
    LOGIN_NO(401,"请重新登录"),
    ERROR(500,"系统错误"),
	REQUEST_PARAM_ERROR(501,"请求参数错误");

    private Integer code;
    private String message;

    ErrorEnum(Integer code, String massage){
        this.code = code;
        this.message = massage;
    }

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
