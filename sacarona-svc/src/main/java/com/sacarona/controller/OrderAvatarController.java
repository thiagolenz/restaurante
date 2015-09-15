package com.sacarona.controller;

import java.io.File;

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
import com.sacarona.model.order.OrderAvatar;
import com.sacarona.service.OrderAvatarService;

@Controller
@RequestMapping(value="/orderAvatar",produces=MediaType.APPLICATION_JSON_VALUE)
public class OrderAvatarController {
	@Autowired
	private OrderAvatarService orderAvatarService; 
	
	@Autowired private FileHandler fileHandler; 
	private RequestContext requestContext;

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public OrderAvatar getByOrderId (@PathVariable("id") Long id) throws BusinessException {
		OrderAvatar orderAvatar = orderAvatarService.findByOrderId (id);
		if (orderAvatar == null)
			orderAvatar = new OrderAvatar();
		return orderAvatar;
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST)
	@ResponseBody
	public OrderAvatar save(@RequestParam("file") MultipartFile fileMultiPart, @RequestParam("orderId") Long orderId) throws BusinessException {
		File file = fileHandler.saveIntoFile(fileMultiPart);
		OrderAvatar avatar = orderAvatarService.save(orderId, file, requestContext.getUser().getId());
		file.delete();
		return avatar;
	}
}
