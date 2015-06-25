package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.LocationTrackDAO;
import com.sacarona.model.track.LocationTrack;

@Repository
public class LocationTrackDaoImpl extends AbstractJpaDaoImpl<LocationTrack> implements LocationTrackDAO {
	
	@Override
	public ServiceCollectionResponse<LocationTrack> findByUser(ServiceRequest<LocationTrack> request) throws UnknownHostException {
		TypedQuery<LocationTrack> query = 
				em.createQuery("from LocationTrack o where o.userId = :userId order by o.trackDate DESC", LocationTrack.class);
		query.setParameter("userId", request.getEntity().getUserId());
		return executeQueryForPagination(query, request);
	}
}
