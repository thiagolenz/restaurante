package com.sacarona.common.svc.controller;

import org.springframework.stereotype.Component;

import com.sacarona.common.context.RequestContext;
import com.sacarona.common.svc.io.ServiceRequest;

@Component
public class ServiceRequestFactory <T> {

	public ServiceRequest<T> createServiceRequest (T entity, RequestContext requestContext) {
		ServiceRequest<T> request = new ServiceRequest<>();
		request.setUser(requestContext.getUser());
		request.setEntity(entity);
		request.setCurrentPage(requestContext.getCurrentPage());
		request.setRecordsRange(requestContext.getLimit());
		return request;
	}
}
