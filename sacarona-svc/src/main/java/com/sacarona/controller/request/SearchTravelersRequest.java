package com.sacarona.controller.request;

import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.travel.Travel;


public class SearchTravelersRequest{
	private ServiceRequest<Travel> request;
	
	private SearchLocationType locationType;

	public SearchTravelersRequest(ServiceRequest<Travel> request,
			SearchLocationType locationType) {
		super();
		this.request = request;
		this.locationType = locationType;
	}
	
	
	public SearchLocationType getLocationType() {
		return locationType;
	}

	public ServiceRequest<Travel> getRequest() {
		return request;
	}
}
