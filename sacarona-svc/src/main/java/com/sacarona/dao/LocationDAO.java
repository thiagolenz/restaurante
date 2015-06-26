package com.sacarona.dao;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.model.world.Location;

public interface LocationDAO extends GenericDAO<Location> {
	ServiceCollectionResponse<Location> search (ServiceRequest<Location> request);
}
