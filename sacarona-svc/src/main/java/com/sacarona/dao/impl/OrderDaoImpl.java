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
				em.createQuery("from Order o where o.userId = :userId order by o.createDate DESC", Order.class);
		query.setParameter("userId", request.getEntity().getUserId());
		return executeQueryForPagination(query, request);
	}
	
	@Transactional
	public Order findDetail(Long id) {
		return findById(Order.class, id);
	}
	
	@Transactional
	public ServiceCollectionResponse<Order> findOrders(SearchOrdersRequest request) throws UnknownHostException {
		Map<String, Object> params = new HashMap<>();
		StringBuilder strQuery = new StringBuilder("from Order o where o.orderStatus = :orderStatus ");
		Order entity = request.getRequest().getEntity();
		
		addCountryOriginWhereClause(strQuery, params, entity);
		
		addCityDestinyWhereClause(request, strQuery, params,  entity);
		addProvinceDestinyWhereClause(request, strQuery, params, entity);
		addCountryDestinyWhereClause(request, strQuery, params, entity);
		
		params.put("orderStatus", OrderStatus.OPEN);
		
		TypedQuery<Order> query = createQueryAndSetParams(strQuery, params, Order.class);

		return executeQueryForPagination(query, request.getRequest());
	}

	private void addCountryOriginWhereClause(StringBuilder query, Map<String, Object> params, Order entity) {
		if (entity.getCountryOrigin() != null) {
			params.put("countryOrigin", entity.getCountryOrigin());
			query.append(" and o.countryOrigin = :countryOrigin");
		}
	}

	private void addCityDestinyWhereClause(SearchOrdersRequest request, StringBuilder query, Map<String, Object> params, Order entity) {
		if (request.getLocationType() == SearchLocationType.CITY && entity.getCityDestiny() != null) {
			params.put("cityDestiny", entity.getCityDestiny());
			query.append(" and o.cityDestiny = :cityDestiny");
		}
	}
	
	private void addProvinceDestinyWhereClause(SearchOrdersRequest request, StringBuilder query, Map<String, Object> params, Order entity) {
		if (request.getLocationType() == SearchLocationType.PROVINCE && entity.getProvinceDestiny() != null) {
			params.put("provinceDestiny", entity.getProvinceDestiny());
			query.append(" and o.provinceDestiny = :provinceDestiny");
		}
	}

	private void addCountryDestinyWhereClause(SearchOrdersRequest request,  StringBuilder query, Map<String, Object> params, Order entity) {
		if (request.getLocationType() == SearchLocationType.COUNTRY && entity.getCountryDestiny() != null) {
			params.put("countryDestiny", entity.getCountryDestiny());
			query.append(" and o.countryDestiny = :countryDestiny");
		}
	}

}
