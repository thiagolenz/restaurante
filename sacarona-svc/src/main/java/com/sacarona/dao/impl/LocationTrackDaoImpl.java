package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.LocationTrackDAO;
import com.sacarona.model.track.LocationTrack;

@Repository
public class LocationTrackDaoImpl extends AbstractDaoImpl<LocationTrack> implements LocationTrackDAO {
	
	@Override
	public ServiceCollectionResponse<LocationTrack> findByUser(ServiceRequest<LocationTrack> request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userId", request.getEntity().getUserId());
		BasicDBObject orderBy = new BasicDBObject("trackDate", -1);
		return executeQueryPatination(request, query, orderBy);
	}

	@Override
	protected String getSequenceName() {
		return "seq_location_track";
	}

	@Override
	protected String getCollectionName() {
		return "location_track";
	}

	@Override
	protected void mapObject(LocationTrack source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("userId", source.getUserId());
		destiny.append("latitude", source.getLatitude());
		destiny.append("longitude", source.getLongitude());
		destiny.append("trackDate", source.getTrackDate());
	}

	@Override
	protected LocationTrack mapResult(BasicDBObject source) {
		LocationTrack track = new LocationTrack();
		track.setId(source.getLong("id"));
		track.setUserId(source.getLong("userId"));
		track.setLongitude(source.getString("longitude"));
		track.setLatitude(source.getString("latitude"));
		track.setTrackDate(source.getDate("trackDate"));
		return track;
	}

}
