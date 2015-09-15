package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.City;

public interface CityDAO extends GenericDAO<City> {
	City findByCountryAndCode (String countryIso, String code) throws UnknownHostException;
	
	City findByExistent (City city);
	
	ServiceCollectionResponse<City> search (ServiceRequest<City> request) throws UnknownHostException;
	
	void completeTheName (City cityTemp, String lang);
}
