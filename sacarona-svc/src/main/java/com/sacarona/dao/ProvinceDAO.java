package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.Province;

public interface ProvinceDAO extends GenericDAO<Province> {
	Province findByAbbreviationAndCountry (String abbreviation, Long id);
	
	ServiceCollectionResponse<Province> search(ServiceRequest<Province> request) throws UnknownHostException;
}
