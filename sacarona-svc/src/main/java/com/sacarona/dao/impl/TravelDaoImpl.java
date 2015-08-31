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
		ServiceCollectionResponse<Travel> result = executeQueryForPagination(query, request);
		completeCityName(request, result);
		return result;
	}
	
	public Travel findDetail(Long id, String lang) {
		Travel result = findById(Travel.class, id);
		completeTravelCitiesName (result, lang);
		return result;
	}
	
	
	public ServiceCollectionResponse<Travel> findTravelers(SearchTravelersRequest request) {
		Map<String, Object> params = new HashMap<>();
		StringBuilder strQuery = new StringBuilder("from Travel o where o.canceled is false and o.departureDate >= :departureDate ");
		Travel entity = request.getRequest().getEntity();
		
		boolean addCountryOriginWhereClause = addCountryOriginWhereClause(strQuery, params, entity);
		boolean addCityDestinyWhereClause = addCityDestinyWhereClause(request, strQuery, params, entity);
		boolean addProvinceDestinyWhereClause = addProvinceDestinyWhereClause(request, strQuery, params, entity);
		
		boolean addCountryDestinyWhereClause = addCountryDestinyWhereClause(request, strQuery, params, entity);
		
		params.put("departureDate", new Date());
		
		if (addCountryOriginWhereClause || addCityDestinyWhereClause || addProvinceDestinyWhereClause || addCountryDestinyWhereClause)
			strQuery.append(" order by o.departureDate DESC");
		else
			strQuery.append(" order by o.id DESC");
		
		TypedQuery<Travel> query = createQueryAndSetParams(strQuery, params, Travel.class);
		
		ServiceCollectionResponse<Travel> result = executeQueryForPagination(query, request.getRequest());
		completeCityName(request.getRequest(), result);
		return result;
	}
	
	private void completeCityName(ServiceRequest<Travel> request, ServiceCollectionResponse<Travel> result) {
		for (Travel travel : result.getDataList()) {
			completeTravelCitiesName(travel, request.getUser().getLang());
		}
	}
	
	private void completeTravelCitiesName (Travel travel, String lang) {
		cityDAO.completeTheName(travel.getCityDestiny(), lang);
		cityDAO.completeTheName(travel.getCityOrigin(), lang);
	}
	
	
	private boolean addCountryOriginWhereClause(StringBuilder query, Map<String, Object> params, Travel entity) {
		if (entity.getCountryOrigin() != null) {
			params.put("countryOrigin", entity.getCountryOrigin());
			query.append(" and o.countryOrigin = :countryOrigin");
			return true;
		}
		return false;
	}

	private boolean addCityDestinyWhereClause(SearchTravelersRequest request, StringBuilder query, Map<String, Object> params, Travel entity) {
		if (request.getLocationType() == SearchLocationType.CITY && entity.getCityDestiny() != null) {
			params.put("cityDestiny", entity.getCityDestiny());
			query.append(" and o.cityDestiny = :cityDestiny");
			return true;
		}
		return false;
	}
	
	private boolean addProvinceDestinyWhereClause(SearchTravelersRequest request, StringBuilder query, Map<String, Object> params, Travel entity) {
		if (request.getLocationType() == SearchLocationType.PROVINCE && entity.getProvinceDestiny() != null) {
			params.put("provinceDestiny", entity.getProvinceDestiny());
			query.append(" and o.provinceDestiny = :provinceDestiny");
			return true;
		}
		return false;
	}

	private boolean addCountryDestinyWhereClause(SearchTravelersRequest request,  StringBuilder query, Map<String, Object> params, Travel entity) {
		if (request.getLocationType() == SearchLocationType.COUNTRY && entity.getCountryDestiny() != null) {
			params.put("countryDestiny", entity.getCountryDestiny());
			query.append(" and o.countryDestiny = :countryDestiny");
			return true;
		}
		return false;
	}

}

