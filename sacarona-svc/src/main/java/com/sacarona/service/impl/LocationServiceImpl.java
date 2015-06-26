package com.sacarona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.LocationDAO;
import com.sacarona.model.world.Location;
import com.sacarona.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService {
	@Autowired private LocationDAO locationDAO;
	
	@Transactional
	public ServiceCollectionResponse<Location> search(ServiceRequest<Location> request) {
		return locationDAO.search(request);
	}

}
