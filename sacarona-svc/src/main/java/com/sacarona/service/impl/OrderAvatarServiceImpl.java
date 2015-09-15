package com.sacarona.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.OrderAvatarDAO;
import com.sacarona.model.order.OrderAvatar;
import com.sacarona.service.FileStorageService;
import com.sacarona.service.OrderAvatarService;

@Service
public class OrderAvatarServiceImpl implements OrderAvatarService {
	@Autowired
	private OrderAvatarDAO avatarDAO;
	
	@Autowired private CredentialsFactory credentialsFactory;
	@Autowired private FileStorageService fileStorageService;

	
	@Transactional
	public OrderAvatar save(Long orderId, File file, Long userId) throws BusinessException {
		OrderAvatar avatar = findByOrderId(orderId);
		if (avatar == null) {
			avatar = new OrderAvatar();
			avatar.setOrderId(orderId);
		}
		String pathNameUser = "user_images/" + userId + "/orders";
		String pathName = fileStorageService.upload(file, pathNameUser, credentialsFactory.getCredentials());
		avatar.setAvatarBase64(pathName);
		if (avatar.getId() == null)
			avatarDAO.insert(avatar);
		else 
			avatarDAO.update(avatar, avatar.getId());
		return avatar;
	}
	
	@Transactional
	public OrderAvatar findByOrderId(Long id) throws BusinessException {
		return avatarDAO.findByOrderId(id);
	}


}
