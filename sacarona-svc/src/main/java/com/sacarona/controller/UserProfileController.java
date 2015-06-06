package com.sacarona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sacarona.common.context.RequestContext;
import com.sacarona.common.response.Response;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.user.UserProfile;
import com.sacarona.service.UserProfileService;


@Controller
@RequestMapping(value="/userProfile",produces=MediaType.APPLICATION_JSON_VALUE)
public class UserProfileController {
	@Autowired
	private UserProfileService userProfileService;
	private RequestContext requestContext;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object getByUserId (@PathVariable("id") Long id) throws BusinessException {
		UserProfile userProfile = userProfileService.findByUserId (id);
		if (userProfile == null)
			return Response.notFoundResponse();
		return userProfile;
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserProfile save(@RequestBody UserProfile profile) {
		profile.setUserId(requestContext.getUser().getId());
		userProfileService.insert(profile);
		return profile;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserProfile update(@RequestBody UserProfile profile, @PathVariable("id") Long id) {
		profile.setUserId(requestContext.getUser().getId());
		userProfileService.update(profile, id);
		return profile;
	}
}
