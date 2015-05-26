package com.sacarona.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sacarona.common.context.RequestContext;
import com.sacarona.common.svc.controller.DefaultBeanValidator;
import com.sacarona.common.svc.controller.ServiceRequestFactory;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.exception.MandatoryFieldException;
import com.sacarona.model.User;
import com.sacarona.service.UserService;

@Controller
@RequestMapping(value="/user", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	@Autowired
	private ServiceRequestFactory<User> serviceFactory;
	private RequestContext requestContext;
		
	@Autowired
	private DefaultBeanValidator beanValidator; 
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/findOrCreate", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public User findOrCreate (@RequestBody @Valid User user, BindingResult result) throws BusinessException, MandatoryFieldException {
		beanValidator.validateMandatoryFields(result);
		return userService.findOrCreate(user);
	}
	
	@RequestMapping(value="/update/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public User update (@RequestBody @Valid User user, @PathVariable ("id") Long id, BindingResult result)
						throws BusinessException, MandatoryFieldException {
		System.out.println(requestContext.getUser());
		userService.updateUser(user, id);
		return user;
	}

}
