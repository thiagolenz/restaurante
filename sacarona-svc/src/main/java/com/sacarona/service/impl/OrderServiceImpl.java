package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.controller.request.SearchOrdersRequest;
import com.sacarona.dao.OrderDAO;
import com.sacarona.model.order.Order;
import com.sacarona.model.order.OrderStatus;
import com.sacarona.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired private OrderDAO orderDAO;

	@Override
	public Order insert(Order order) {
		order.setCreateDate(new Date());
		order.setOrderStatus(OrderStatus.OPEN);
		return orderDAO.insert(order);
	}

	@Override
	public Order update(Order order, Long id) {
		orderDAO.update(order, id);
		return order;
	}

	@Override
	public void remove(Long orderId) {
		orderDAO.remove(orderDAO.findById(Order.class, orderId));
	}

	@Override
	public ServiceCollectionResponse<Order> findOrdersByUser(ServiceRequest<Order> request) throws BusinessException {
		try {
			return orderDAO.findOrdersByUser(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}
	
	@Override
	public ServiceCollectionResponse<Order> findOrders(SearchOrdersRequest request) throws BusinessException {
		try {
			return orderDAO.findOrders(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

}
