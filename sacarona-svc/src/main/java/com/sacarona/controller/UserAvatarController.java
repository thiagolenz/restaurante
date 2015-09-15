package com.sacarona.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sacarona.common.FileHandler;
import com.sacarona.common.context.RequestContext;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.user.UserAvatar;
import com.sacarona.service.UserAvatarService;

@Controller
@RequestMapping(value="/userAvatar",produces=MediaType.APPLICATION_JSON_VALUE)
public class UserAvatarController {
	
	@Autowired private UserAvatarService userAvatarService;
	@Autowired private FileHandler fileHandler; 
	private RequestContext requestContext;

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public UserAvatar getByUserId (@PathVariable("id") Long id) throws BusinessException {
		UserAvatar userAvatar = userAvatarService.findByUserId (id);
		if (userAvatar == null)
			userAvatar = new UserAvatar();
		return userAvatar;
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	@ResponseBody
	public UserAvatar convertFromFile (@RequestParam("file") MultipartFile fileMultiPart) throws BusinessException, IOException {
		File file = fileHandler.saveIntoFile(fileMultiPart);
		UserAvatar avatar = userAvatarService.save(requestContext.getUser(), file);
		file.delete();
		return avatar;
	}
}
