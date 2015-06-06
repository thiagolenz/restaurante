package com.sacarona.common.svc.handlers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sacarona.common.ErrorResponseBean;
import com.sacarona.common.i18n.MessageBundleService;
import com.sacarona.common.svc.exception.BusinessException;

/**
 * Handles the exception of BusinessException, translate the messages and return the bean response
 * @author thiagolenz
 * @since Aug 26, 2014
 *
 */
@ControllerAdvice
public class BusinessExceptionHandler {
	
	@Autowired
	private MessageBundleService bundleService;
	
	@ExceptionHandler(value = BusinessException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
	public ErrorResponseBean defaultErrorHandler(HttpServletRequest req, BusinessException e) throws Exception {
		ErrorResponseBean errorBean = new ErrorResponseBean();
		errorBean.setErrorCode("01");
		errorBean.setErrorDescription(bundleService.translate(e.getMessage()));
		return errorBean;
	}
}
