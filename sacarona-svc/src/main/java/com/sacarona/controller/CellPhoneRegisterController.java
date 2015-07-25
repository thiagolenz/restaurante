package com.sacarona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sacarona.common.context.RequestContext;
import com.sacarona.common.response.Response;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.mobile.CellPhoneRegister;
import com.sacarona.service.CellPhoneRegisterService;
import com.sacarona.service.NotifyMobileService;

@Controller
@RequestMapping(value="/cellPhoneRegister",produces=MediaType.APPLICATION_JSON_VALUE)
public class CellPhoneRegisterController {
	private RequestContext requestContext;
	
	@Autowired
	private CellPhoneRegisterService cellPhoneRegisterService;
	
	@Autowired
	private NotifyMobileService mobileService;
	
	@RequestMapping(value="/connect", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CellPhoneRegister create (@RequestBody CellPhoneRegister cellPhoneRegister) throws BusinessException {
		cellPhoneRegister.setUserId(requestContext.getUser().getId());
		return cellPhoneRegisterService.updateConnect(cellPhoneRegister);
	}
	
	@RequestMapping(value="/testNotification", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response testNotification () throws BusinessException {
		mobileService.notify("TEST", "sacarona.notification.testconnection", requestContext.getUser().getId());
		return Response.newSuccessResponse();
	}
	
}
