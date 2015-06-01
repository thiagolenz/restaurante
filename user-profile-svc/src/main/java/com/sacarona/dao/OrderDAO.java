package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.order.Order;

public interface OrderDAO extends GenericDAO<Order> {
	ServiceCollectionResponse<Order> findOrdersByUser(ServiceRequest<Order> request) throws UnknownHostException;
}