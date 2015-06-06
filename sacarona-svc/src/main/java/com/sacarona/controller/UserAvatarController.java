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
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.user.UserAvatar;
import com.sacarona.service.UserAvatarService;

@Controller
@RequestMapping(value="/userAvatar",produces=MediaType.APPLICATION_JSON_VALUE)
public class UserAvatarController {
	
	@Autowired
	private UserAvatarService userAvatarService; 
	private RequestContext requestContext;

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserAvatar getByUserId (@PathVariable("id") Long id) throws BusinessException {
		UserAvatar userAvatar = userAvatarService.findByUserId (id);
		if (userAvatar == null)
			userAvatar = new UserAvatar();
		return userAvatar;
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserAvatar save(@RequestBody UserAvatar avatar) {
		if (avatar.getUserId() == null)
			avatar.setUserId(requestContext.getUser().getId());
		userAvatarService.insert(avatar);
		return avatar;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserAvatar update(@RequestBody UserAvatar avatar, @PathVariable("id") Long id) {
		if (avatar.getUserId() == null)
			avatar.setUserId(requestContext.getUser().getId());
		userAvatarService.update(avatar, id);
		return avatar;
	}
}
