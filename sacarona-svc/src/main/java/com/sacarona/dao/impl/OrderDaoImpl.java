package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.controller.request.SearchLocationType;
import com.sacarona.controller.request.SearchOrdersRequest;
import com.sacarona.dao.CityDAO;
import com.sacarona.dao.CountryDAO;
import com.sacarona.dao.OrderDAO;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.model.order.Order;
import com.sacarona.model.order.OrderStatus;

@Repository
public class OrderDaoImpl extends AbstractJpaDaoImpl<Order> implements OrderDAO {
	
	@Autowired private CityDAO cityDAO;
	@Autowired private CountryDAO countryDAO;
	@Autowired private ProvinceDAO provinceDAO;
	
	@Transactional
	public ServiceCollectionResponse<Order> findOrdersByUser(ServiceRequest<Order> request) throws UnknownHostException {
		TypedQuery<Order> query = 
				em.createQuery("from Order o where o.userId = :userId and o.removed is false order by o.createDate DESC", Order.class);
		query.setParameter("userId", request.getEntity().getUserId());
		ServiceCollectionResponse<Order> result = executeQueryForPagination(query, request);
		completeCityName(request, result);
		return result;
	}
	
	@Transactional
	public Order findDetail(Long id, String lang) {
		Order result = findById(Order.class, id);
		completeCitiesName(result, lang);
		return result;
	}
	
	@Transactional
	public ServiceCollectionResponse<Order> findOrders(SearchOrdersRequest request) throws UnknownHostException {
		Map<String, Object> params = new HashMap<>();
		StringBuilder strQuery = new StringBuilder("from Order o where o.orderStatus = :orderStatus ");
		strQuery.append(" and o.userId != :userId and o.removed is false ");
		Order entity = request.getRequest().getEntity();
		
		boolean addCountryOriginWhereClause = addCountryOriginWhereClause(strQuery, params, entity);
		
		boolean addCityDestinyWhereClause = addCityDestinyWhereClause(request, strQuery, params,  entity);
		boolean addProvinceDestinyWhereClause = addProvinceDestinyWhereClause(request, strQuery, params, entity);
		boolean addCountryDestinyWhereClause = addCountryDestinyWhereClause(request, strQuery, params, entity);
		
		params.put("orderStatus", OrderStatus.OPEN);
		params.put("userId", request.getRequest().getUser().getId());
		
		if (addCountryOriginWhereClause || addCityDestinyWhereClause || addProvinceDestinyWhereClause || addCountryDestinyWhereClause)
			strQuery.append(" order by o.createDate DESC");
		else
			strQuery.append(" order by o.id DESC");
		
		TypedQuery<Order> query = createQueryAndSetParams(strQuery, params, Order.class);

		ServiceCollectionResponse<Order> result = executeQueryForPagination(query, request.getRequest());
		completeCityName(request.getRequest(), result);
		return result;
	}
	
	private void completeCityName(ServiceRequest<Order> request, ServiceCollectionResponse<Order> result) {
		for (Order order : result.getDataList()) {
			completeCitiesName(order, request.getUser().getLang());
		}
	}
	
	private void completeCitiesName (Order order, String lang) {
		cityDAO.completeTheName(order.getCityDestiny(), lang);
	}

	private boolean addCountryOriginWhereClause(StringBuilder query, Map<String, Object> params, Order entity) {
		if (entity.getCountryOrigin() != null) {
			params.put("countryOrigin", entity.getCountryOrigin());
			query.append(" and o.countryOrigin = :countryOrigin");
			return true;
		}
		return false;
	}

	private boolean addCityDestinyWhereClause(SearchOrdersRequest request, StringBuilder query, Map<String, Object> params, Order entity) {
		if (request.getLocationType() == SearchLocationType.CITY && entity.getCityDestiny() != null) {
			params.put("cityDestiny", entity.getCityDestiny());
			query.append(" and o.cityDestiny = :cityDestiny");
			return true;
		}
		return false;
	}
	
	private boolean addProvinceDestinyWhereClause(SearchOrdersRequest request, StringBuilder query, Map<String, Object> params, Order entity) {
		if (request.getLocationType() == SearchLocationType.PROVINCE && entity.getProvinceDestiny() != null) {
			params.put("provinceDestiny", entity.getProvinceDestiny());
			query.append(" and o.provinceDestiny = :provinceDestiny");
			return true;
		}
		return false;
	}

	private boolean addCountryDestinyWhereClause(SearchOrdersRequest request,  StringBuilder query, Map<String, Object> params, Order entity) {
		if (request.getLocationType() == SearchLocationType.COUNTRY && entity.getCountryDestiny() != null) {
			params.put("countryDestiny", entity.getCountryDestiny());
			query.append(" and o.countryDestiny = :countryDestiny");
			return true;
		}
		return false;
	}

}
