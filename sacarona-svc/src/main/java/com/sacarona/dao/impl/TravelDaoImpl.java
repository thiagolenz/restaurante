package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.controller.request.SearchLocationType;
import com.sacarona.controller.request.SearchTravelersRequest;
import com.sacarona.dao.CityDAO;
import com.sacarona.dao.CountryDAO;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.dao.TravelDAO;
import com.sacarona.model.travel.Travel;
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

@Repository
public class TravelDaoImpl extends AbstractDaoImpl<Travel> implements TravelDAO {
	
	@Autowired private CityDAO cityDAO;
	@Autowired private CountryDAO countryDAO;
	@Autowired private ProvinceDAO provinceDAO;
	
	@Override
	public ServiceCollectionResponse<Travel> findByUser(ServiceRequest<Travel> request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userId", request.getEntity().getUserId());
		BasicDBObject orderBy = new BasicDBObject("createDate", -1);
		ServiceCollectionResponse<Travel> response = executeQueryPatination(request, query, orderBy);
		for (Travel travel : response.getDataList()) {
			fillData(travel);
		}
		return response;
	}
	
	@Override
	public ServiceCollectionResponse<Travel> findTravelers(SearchTravelersRequest request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		Travel entity = request.getRequest().getEntity();
		
		addCountryOriginWhereClause(query, entity);
		addCityDestinyWhereClause(request, query, entity);
		addProvinceDestinyWhereClause(request, query, entity);
		
		addCountryDestinyWhereClause(request, query, entity);
		query.append("canceled", false);
		query.append("departureDate", new BasicDBObject("$gte", new Date()));

		BasicDBObject orderBy = new BasicDBObject("departureDate", -1);
		ServiceCollectionResponse<Travel> response = executeQueryPatination(request.getRequest(), query, orderBy);
		for (Travel travel : response.getDataList()) {
			fillData(travel);
		}
		return response;
	}

	private void addCountryOriginWhereClause(BasicDBObject query, Travel entity) {
		if (entity.getCountryOrigin() != null)
			query.append("countryOrigin_id", entity.getCountryOrigin().getId());
	}

	private void addCityDestinyWhereClause(SearchTravelersRequest request, BasicDBObject query, Travel entity) {
		if (request.getLocationType() == SearchLocationType.CITY && entity.getCityDestiny() != null) 
			query.append("cityDestiny_id", entity.getCityDestiny().getId());
	}
	
	private void addProvinceDestinyWhereClause(SearchTravelersRequest request,BasicDBObject query, Travel entity) {
		if (request.getLocationType() == SearchLocationType.PROVINCE && entity.getProvinceDestiny() != null)
			query.append("provinceDestiny_id", entity.getProvinceDestiny().getId());
	}

	private void addCountryDestinyWhereClause(SearchTravelersRequest request, BasicDBObject query, Travel entity) {
		if (request.getLocationType() == SearchLocationType.COUNTRY && entity.getCountryDestiny() != null)
			query.append("countryDestiny_id", entity.getCountryDestiny().getId());
	}

	@Override
	protected String getSequenceName() {
		return "seq_travel";
	}

	@Override
	protected String getCollectionName() {
		return "travel";
	}

	@Override
	protected void mapObject(Travel source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("countryOrigin_id", source.getCountryOrigin().getId());
		destiny.append("provinceOrigin_id", source.getProvinceOrigin().getId());
		destiny.append("cityOrigin_id", source.getCityOrigin().getId());
		destiny.append("countryDestiny_id", source.getCountryDestiny().getId());
		destiny.append("provinceDestiny_id", source.getProvinceDestiny().getId());
		destiny.append("cityDestiny_id", source.getCityDestiny().getId());
		destiny.append("departureDate", source.getDepartureDate());
		destiny.append("backDate", source.getBackDate());
		destiny.append("ticketPictureBase64", source.getTicketPictureBase64());
		destiny.append("confirmed", source.isConfirmed());
		destiny.append("canceled", source.isCanceled());
		destiny.append("createDate", source.getCreateDate());
		destiny.append("userId", source.getUserId());
	}

	@Override
	protected Travel mapResult(BasicDBObject source) {
		Travel travel = new Travel();
		travel.setId(source.getLong("id"));
		travel.setCountryOrigin(new Country(source.getLong("countryOrigin_id")));
		travel.setProvinceOrigin(new Province(source.getLong("provinceOrigin_id")));
		travel.setCityOrigin(new City(source.getLong("cityOrigin_id")));
		
		travel.setCountryDestiny(new Country(source.getLong("countryDestiny_id")));
		travel.setProvinceDestiny(new Province(source.getLong("provinceDestiny_id")));
		travel.setCityDestiny(new City(source.getLong("cityDestiny_id")));
		
		travel.setDepartureDate(source.getDate("departureDate"));
		travel.setBackDate(source.getDate("backDate"));
		travel.setTicketPictureBase64(source.getString("ticketPictureBase64"));
		travel.setConfirmed(source.getBoolean("confirmed"));
		travel.setCanceled(source.getBoolean("canceled"));
		travel.setCreateDate(source.getDate("createDate"));
		travel.setUserId(source.getLong("userId"));
		return travel;
	}
	
	private void fillData (Travel travel) {
		travel.setCityOrigin(cityDAO.findById(City.class, travel.getCityOrigin().getId()));
		travel.setProvinceOrigin(provinceDAO.findById(Province.class, travel.getProvinceOrigin().getId()));
		travel.setCountryOrigin(countryDAO.findById(Country.class, travel.getCountryOrigin().getId()));
		
		travel.setCityDestiny(cityDAO.findById(City.class, travel.getCityDestiny().getId()));
		travel.setProvinceDestiny(provinceDAO.findById(Province.class, travel.getProvinceDestiny().getId()));
		travel.setCountryDestiny(countryDAO.findById(Country.class, travel.getCountryDestiny().getId()));
	}

}
