package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.controller.request.SearchOrdersRequest;
import com.sacarona.model.order.Order;

public interface OrderDAO extends GenericDAO<Order> {
	ServiceCollectionResponse<Order> findOrdersByUser(ServiceRequest<Order> request) throws UnknownHostException;
	
	ServiceCollectionResponse<Order> findOrders (SearchOrdersRequest request) throws UnknownHostException;
	
	Order findDetail(Long id, String lang) ;
}
