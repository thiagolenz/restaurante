package com.sacarona.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sacarona.common.svc.controller.DefaultBeanValidator;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.exception.MandatoryFieldException;
import com.sacarona.model.User;
import com.sacarona.model.mobile.AppUserAuth;
import com.sacarona.service.AppUserAuthService;

@Controller
@RequestMapping(value="/app/auth",produces=MediaType.APPLICATION_JSON_VALUE)
public class UserAuthController {
	@Autowired
	private DefaultBeanValidator beanValidator; 
	
	@Autowired
	private AppUserAuthService appUserAuthService;
	
	@RequestMapping(value="/create", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public AppUserAuth create (@RequestBody @Valid User user, BindingResult result) throws BusinessException, MandatoryFieldException {
		beanValidator.validateMandatoryFields(result);
		return appUserAuthService.createNew(user);
	}
}
