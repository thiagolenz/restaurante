package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.City;

public interface CityDAO extends GenericDAO<City> {
	City findByStateAndCode (String countryIso, String code) throws UnknownHostException;
	
	ServiceCollectionResponse<City> search (ServiceRequest<City> request) throws UnknownHostException;
}
