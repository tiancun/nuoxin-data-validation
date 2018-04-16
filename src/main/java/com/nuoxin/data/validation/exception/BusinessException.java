package com.nuoxin.data.validation.exception;

/**
 * 业务异常
 * @author tiancun
 * @date 2018-04-13
 */
public class BusinessException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3955221681828820550L;

	public BusinessException() {

	}

	public BusinessException(Integer code, String codeMessage) {
		super(code, codeMessage);
	}

	public BusinessException(Integer code, String codeMessage, String message) {
		super(message);
		setCode(code);
		setCodeMessage(codeMessage);
	}

	public BusinessException(Integer code, String codeMessage, String message, Throwable cause) {
		super(message, cause);
		setCode(code);
		setCodeMessage(codeMessage);
	}

}
