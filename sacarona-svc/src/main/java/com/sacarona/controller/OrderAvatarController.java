package com.sacarona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.order.OrderAvatar;
import com.sacarona.service.OrderAvatarService;

@Controller
@RequestMapping(value="/orderAvatar",produces=MediaType.APPLICATION_JSON_VALUE)
public class OrderAvatarController {
	@Autowired
	private OrderAvatarService orderAvatarService; 

	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	@ResponseBody
	public OrderAvatar getByOrderId (@PathVariable("id") Long id) throws BusinessException {
		OrderAvatar orderAvatar = orderAvatarService.findByOrderId (id);
		if (orderAvatar == null)
			orderAvatar = new OrderAvatar();
		return orderAvatar;
	}
	
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OrderAvatar save(@RequestBody OrderAvatar avatar) {
		orderAvatarService.insert(avatar);
		return avatar;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OrderAvatar update(@RequestBody OrderAvatar avatar, @PathVariable("id") Long id) {
		orderAvatarService.update(avatar, id);
		return avatar;
	}
}
