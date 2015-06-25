package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.controller.request.SearchLocationType;
import com.sacarona.controller.request.SearchTravelersRequest;
import com.sacarona.dao.CityDAO;
import com.sacarona.dao.CountryDAO;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.dao.TravelDAO;
import com.sacarona.model.travel.Travel;

@Repository
public class TravelDaoImpl extends AbstractJpaDaoImpl<Travel> implements TravelDAO {
	
	@Autowired private CityDAO cityDAO;
	@Autowired private CountryDAO countryDAO;
	@Autowired private ProvinceDAO provinceDAO;
	
	
	public ServiceCollectionResponse<Travel> findByUser(ServiceRequest<Travel> request) throws UnknownHostException {
		TypedQuery<Travel> query = 
				em.createQuery("from Travel o where o.userId = :userId order by o.createDate DESC", Travel.class);
		query.setParameter("userId", request.getEntity().getUserId());
		return executeQueryForPagination(query, request);
	}
	
	
	public Travel findDetail(Long id) {
		return findById(Travel.class, id);
	}
	
	
	public ServiceCollectionResponse<Travel> findTravelers(SearchTravelersRequest request) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder strQuery = new StringBuilder("from Travel o where o.canceled is false and o.departureDate >= :departureDate ");
		Travel entity = request.getRequest().getEntity();
		
		addCountryOriginWhereClause(strQuery, params, entity);
		addCityDestinyWhereClause(request, strQuery, params, entity);
		addProvinceDestinyWhereClause(request, strQuery, params, entity);
		
		addCountryDestinyWhereClause(request, strQuery, params, entity);
		params.put("departureDate", new Date());
		
		strQuery.append(" order by o.departureDate DESC");
		TypedQuery<Travel> query = createQueryAndSetParams(strQuery, params, Travel.class);
		return executeQueryForPagination(query, request.getRequest());
	}
	
	private void addCountryOriginWhereClause(StringBuilder query, Map<String, Object> params, Travel entity) {
		if (entity.getCountryOrigin() != null) {
			params.put("countryOrigin", entity.getCountryOrigin());
			query.append(" and o.countryOrigin = :countryOrigin");
		}
	}

	private void addCityDestinyWhereClause(SearchTravelersRequest request, StringBuilder query, Map<String, Object> params, Travel entity) {
		if (request.getLocationType() == SearchLocationType.CITY && entity.getCityDestiny() != null) {
			params.put("cityDestiny", entity.getCityDestiny());
			query.append(" and o.cityDestiny = :cityDestiny");
		}
	}
	
	private void addProvinceDestinyWhereClause(SearchTravelersRequest request, StringBuilder query, Map<String, Object> params, Travel entity) {
		if (request.getLocationType() == SearchLocationType.PROVINCE && entity.getProvinceDestiny() != null) {
			params.put("provinceDestiny", entity.getProvinceDestiny());
			query.append(" and o.provinceDestiny = :provinceDestiny");
		}
	}

	private void addCountryDestinyWhereClause(SearchTravelersRequest request,  StringBuilder query, Map<String, Object> params, Travel entity) {
		if (request.getLocationType() == SearchLocationType.COUNTRY && entity.getCountryDestiny() != null) {
			params.put("countryDestiny", entity.getCountryDestiny());
			query.append(" and o.countryDestiny = :countryDestiny");
		}
	}

}
