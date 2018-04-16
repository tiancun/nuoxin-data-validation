package com.nuoxin.data.validation.exception;


import com.nuoxin.data.validation.enums.ErrorEnum;

/**
 * 异常的基类
 * @author tiancun
 * @date 2018-04-13
 */
public class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4864323350649929229L;

	/**
	 * 错误码
	 */
	protected Integer code = ErrorEnum.ERROR.getCode();

	/**
	 * 错误码说明
	 */
	protected String codeMessage = ErrorEnum.ERROR.getMessage();

	public BaseException() {
		super();
	}
	
	public BaseException(Integer code, String codeMessage) {
		this.code = code;
		this.codeMessage = codeMessage;
	}
	

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}


	public Integer getCode() {
		return code;
	}


	public void setCode(Integer code) {
		this.code = code;
	}


	public String getCodeMessage() {
		return codeMessage;
	}


	public void setCodeMessage(String codeMessage) {
		this.codeMessage = codeMessage;
	}
	
	
}
