package com.sacarona.service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.controller.request.SearchOrdersRequest;
import com.sacarona.model.order.Order;

public interface OrderService {
	Order insert (Order order);
	
	Order update (Order order, Long id);
	
	void remove (Long orderId);
	
	ServiceCollectionResponse<Order> findOrdersByUser(ServiceRequest<Order> request) throws BusinessException;
	
	ServiceCollectionResponse<Order> findOrders (SearchOrdersRequest request) throws BusinessException;
}
