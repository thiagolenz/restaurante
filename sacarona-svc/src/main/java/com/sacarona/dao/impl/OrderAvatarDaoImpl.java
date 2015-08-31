package com.sacarona.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.dao.OrderAvatarDAO;
import com.sacarona.model.order.OrderAvatar;

@Repository
public class OrderAvatarDaoImpl extends AbstractJpaDaoImpl<OrderAvatar> implements
		OrderAvatarDAO {

	@Override
	public OrderAvatar findByOrderId (Long id){
		TypedQuery<OrderAvatar> query = em.createQuery("from OrderAvatar o where o.orderId = :orderId ", OrderAvatar.class);
		query.setParameter("orderId", id);
		return singleQuery(query);
	}
}
