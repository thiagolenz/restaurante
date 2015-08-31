package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.order.OrderAvatar;

public interface OrderAvatarService {
	OrderAvatar findByOrderId (Long id) throws BusinessException;
	void insert (OrderAvatar avatar);
	void update (OrderAvatar avatar, Long id);
}
