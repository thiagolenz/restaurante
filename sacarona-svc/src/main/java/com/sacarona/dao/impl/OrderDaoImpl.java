package com.sacarona.dao.impl;

import java.math.BigDecimal;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
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
import com.sacarona.model.world.City;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDAO {
	
	@Autowired private CityDAO cityDAO;
	@Autowired private CountryDAO countryDAO;
	@Autowired private ProvinceDAO provinceDAO;
	
	@Override
	public ServiceCollectionResponse<Order> findOrdersByUser(ServiceRequest<Order> request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userId", request.getEntity().getUserId());
		BasicDBObject orderBy = new BasicDBObject("createDate", -1);
		ServiceCollectionResponse<Order> response = executeQueryPatination(request, query, orderBy);
		for (Order order : response.getDataList()) {
			fillData(order);
		}
		return response;
	}
	
	public Order findDetail(Long id) {
		Order order = findById(Order.class, id);
		fillData(order);
		return order;
	}
	
	@Override
	public ServiceCollectionResponse<Order> findOrders(SearchOrdersRequest request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		Order entity = request.getRequest().getEntity();
		
		addCountryOriginWhereClause(query, entity);
		
		addCityDestinyWhereClause(request, query, entity);
		addProvinceDestinyWhereClause(request, query, entity);
		addCountryDestinyWhereClause(request, query, entity);
		
		query.append("orderStatus", OrderStatus.OPEN.toString());

		ServiceCollectionResponse<Order> response = executeQueryPatination(request.getRequest(), query);
		for (Order order : response.getDataList()) {
			fillData(order);
		}
		return response;
	}

	private void addCountryOriginWhereClause(BasicDBObject query, Order entity) {
		if (entity.getCountryOrigin() != null)
			query.append("countryOrigin_id", entity.getCountryOrigin().getId());
	}

	private void addCityDestinyWhereClause(SearchOrdersRequest request, BasicDBObject query, Order entity) {
		if (request.getLocationType() == SearchLocationType.CITY && entity.getCityDestiny() != null) 
			query.append("cityDestiny_id", entity.getCityDestiny().getId());
	}
	
	private void addProvinceDestinyWhereClause(SearchOrdersRequest request,BasicDBObject query, Order entity) {
		if (request.getLocationType() == SearchLocationType.PROVINCE && entity.getProvinceDestiny() != null)
			query.append("provinceDestiny_id", entity.getProvinceDestiny().getId());
	}

	private void addCountryDestinyWhereClause(SearchOrdersRequest request, BasicDBObject query, Order entity) {
		if (request.getLocationType() == SearchLocationType.COUNTRY && entity.getCountryDestiny() != null)
			query.append("countryDestiny_id", entity.getCountryDestiny().getId());
	}


	@Override
	protected String getSequenceName() {
		return "seq_order";
	}

	@Override
	protected String getCollectionName() {
		return "order";
	}

	@Override
	protected void mapObject(Order source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("productName", source.getProductName());
		destiny.append("productBrand", source.getProductBrand());
		destiny.append("productPrice", source.getProductPrice());
		destiny.append("productDescription", source.getProductDescription());
		destiny.append("productImageBase64", source.getProductImageBase64());
		destiny.append("countryDestiny_id", source.getCountryDestiny().getId());
		destiny.append("countryOrigin_id", source.getCountryOrigin().getId());
		destiny.append("provinceDestiny_id", source.getProvinceDestiny().getId());
		destiny.append("cityDestiny_id", source.getCityDestiny().getId());
		destiny.append("wishDeliveryDate", source.getWishDeliveryDate());
		destiny.append("bonus", source.getBonus().doubleValue());
		destiny.append("userId", source.getUserId());
		destiny.append("createDate", source.getCreateDate());
		destiny.append("orderStatus", source.getOrderStatus().toString());
	}

	@Override
	protected Order mapResult(BasicDBObject source) {
		Order order = new Order();
		order.setId(source.getLong("id"));
		order.setProductName(source.getString("productName"));
		order.setProductBrand(source.getString("productBrand"));
		order.setProductPrice(source.getString("productPrice"));
		order.setProductDescription(source.getString("productDescription"));
		order.setProductImageBase64(source.getString("productImageBase64"));
		order.setCountryDestiny(new Country(source.getLong("countryDestiny_id")));
		order.setCountryOrigin(new Country(source.getLong("countryOrigin_id")));
		order.setProvinceDestiny(new Province(source.getLong("provinceDestiny_id")));
		order.setCityDestiny(new City(source.getLong("cityDestiny_id")));
		order.setWishDeliveryDate(source.getDate("wishDeliveryDate"));
		order.setBonus(new BigDecimal(source.getDouble("bonus")));
		order.setUserId(source.getLong("userId"));
		order.setCreateDate(source.getDate("createDate"));
		order.setOrderStatus(OrderStatus.valueOf(source.getString("orderStatus")));
		return order;
	}
	
	private void fillData (Order order) {
		order.setCityDestiny(cityDAO.findById(City.class, order.getCityDestiny().getId()));
		order.setProvinceDestiny(provinceDAO.findById(Province.class, order.getProvinceDestiny().getId()));
		order.setCountryDestiny(countryDAO.findById(Country.class, order.getCountryDestiny().getId()));
		order.setCountryOrigin(countryDAO.findById(Country.class, order.getCountryOrigin().getId()));
	}

}
