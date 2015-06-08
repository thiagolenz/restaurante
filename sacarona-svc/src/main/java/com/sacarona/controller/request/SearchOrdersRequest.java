package com.sacarona.controller.request;

import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.order.Order;


public class SearchOrdersRequest{
	private ServiceRequest<Order> request;
	
	private SearchLocationType locationType;

	public SearchOrdersRequest(ServiceRequest<Order> request,
			SearchLocationType locationType) {
		super();
		this.request = request;
		this.locationType = locationType;
	}
	
	
	public SearchLocationType getLocationType() {
		return locationType;
	}

	public ServiceRequest<Order> getRequest() {
		return request;
	}
}
