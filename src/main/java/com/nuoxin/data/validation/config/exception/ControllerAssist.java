package com.nuoxin.data.validation.config.exception;


import com.nuoxin.data.validation.common.DefaultResponseBean;
import com.nuoxin.data.validation.enums.ErrorEnum;
import com.nuoxin.data.validation.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;

import javax.mail.Multipart;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;


/**
 * 统一的异常返回格式
 * @author tiancun
 * @date 2018-04-13
 */
@ControllerAdvice
public class ControllerAssist {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 单个文件上传限制大小
	 */
	@Value("spring.http.multipart.maxFileSize")
	private String springHttpMultipartMaxFileSize;

	/**
	 * 设置总上传的数据大小
	 */
	@Value("spring.http.multipart.maxRequestSize")
	private String springHttpMultipartMaxRequestSize;

	/**
	 * 注册全局数据编辑器，若传递的数据为空字串 转成 null
	 * 
	 * @param binder
	 *          数据绑定
	 * @param request
	 *          web请求
	 */
	@InitBinder
	public void registerCustomEditors(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public ResponseEntity<DefaultResponseBean<?>> handleBusinessException(BusinessException exception){
		logger.error(exception.getCodeMessage(),exception);
		return ResponseEntity.ok(DefaultResponseBean.getDefaultResponseBean(exception.getCode(),exception.getCodeMessage(),exception.getMessage()));
	}




	/**
	 * 捕获文件上传限制异常
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	public ResponseEntity<DefaultResponseBean<?>> handleMultipartException(MultipartException exception) {
		logger.error(exception.getMessage(), exception);
		ErrorEnum error = ErrorEnum.ERROR;
		String message = error.getMessage();
		//String codeMessage = "上传的单个文件不能超过:" + springHttpMultipartMaxFileSize + "总大小不能超过:" + springHttpMultipartMaxRequestSize;
        String codeMessage = "上传的单个文件不能超过:5M,总大小不能超过:10M" ;
		return ResponseEntity.ok(DefaultResponseBean.getDefaultResponseBean(error.getCode(), message, codeMessage));
	}



	/**
	 * 处理请求参数验证异常
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<DefaultResponseBean<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		logger.error(exception.getMessage());
		logger.error("", exception);
		ErrorEnum error = ErrorEnum.ERROR;
		String message = error.getMessage();
		BindingResult bindingResult = exception.getBindingResult();
		if (bindingResult != null && bindingResult.hasErrors()) {
			List<ObjectError> objectErrorList = bindingResult.getAllErrors();
			if (!objectErrorList.isEmpty()) {
				message = objectErrorList.get(0).getDefaultMessage();
			}
		}
		return ResponseEntity.ok(DefaultResponseBean.getDefaultResponseBean(error.getCode(), message, exception.getMessage()));
	}
	
	
	/**
	 * 处理服务器端数据访问错误
	 * @param exception
	 * @return
	 */
	@ExceptionHandler({ SQLException.class, DataAccessException.class, DataAccessResourceFailureException.class, DataIntegrityViolationException.class })
	@ResponseBody
	public ResponseEntity<DefaultResponseBean<?>> handleSQLException(Exception exception) {
		logger.error(exception.getMessage(),exception);
		return ResponseEntity.ok(DefaultResponseBean.getDefaultResponseBean(ErrorEnum.ERROR.getCode(),ErrorEnum.ERROR.getMessage(),exception.getMessage()));
	}
	
	
	/**
	 * 处理服务器端RuntimeException
	 * 
	 * @param request
	 *          请求对象
	 * @param exception
	 *          异常对象
	 * @param locale
	 *          地理信息
	 * @return
	 */
	@ExceptionHandler({ RuntimeException.class })
	@ResponseBody
	public ResponseEntity<DefaultResponseBean<?>> handleAllException(HttpServletRequest request, Exception exception, Locale locale) {
		logger.error(exception.getMessage(),exception);
		return ResponseEntity.ok(DefaultResponseBean.getDefaultResponseBean(ErrorEnum.ERROR.getCode(),ErrorEnum.ERROR.getMessage(),exception.getMessage()));
	}

}
