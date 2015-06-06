package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.Country;

public interface CountryDAO extends GenericDAO<Country> {
	Country findByExternalId (Long id) throws UnknownHostException;
	
	ServiceCollectionResponse<Country> search (ServiceRequest<Country> request) throws UnknownHostException;
	
	Country findByIsoCode (String code) throws UnknownHostException;
}
