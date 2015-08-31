package com.sacarona.dao;

import com.sacarona.model.order.OrderAvatar;

public interface OrderAvatarDAO extends GenericDAO<OrderAvatar> {
	OrderAvatar findByOrderId (Long id);
}
