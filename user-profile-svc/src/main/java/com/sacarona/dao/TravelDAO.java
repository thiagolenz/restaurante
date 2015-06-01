package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.travel.Travel;

public interface TravelDAO extends GenericDAO<Travel> {
	ServiceCollectionResponse<Travel> findByUser(ServiceRequest<Travel> request) throws UnknownHostException;
}
