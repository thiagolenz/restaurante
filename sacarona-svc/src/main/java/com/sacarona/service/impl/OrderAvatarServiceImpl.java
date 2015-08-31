package com.sacarona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.OrderAvatarDAO;
import com.sacarona.model.order.OrderAvatar;
import com.sacarona.service.OrderAvatarService;

@Service
public class OrderAvatarServiceImpl implements OrderAvatarService {
	@Autowired
	private OrderAvatarDAO avatarDAO;

	@Transactional
	public OrderAvatar findByOrderId(Long id) throws BusinessException {
		return avatarDAO.findByOrderId(id);
	}

	@Transactional
	public void insert(OrderAvatar avatar) {
		avatarDAO.insert(avatar);
	}

	@Transactional
	public void update(OrderAvatar avatar, Long id) {
		avatarDAO.update(avatar, id);
	}
}
