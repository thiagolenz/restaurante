package com.sacarona.service;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.Location;

public interface LocationService {
	ServiceCollectionResponse<Location> search (ServiceRequest<Location> request);
}
