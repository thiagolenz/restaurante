package com.sacarona.service;

import java.io.File;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.order.OrderAvatar;

public interface OrderAvatarService {
	OrderAvatar findByOrderId (Long id) throws BusinessException;
	OrderAvatar save (Long orderId, File file,Long userId) throws BusinessException;
}
