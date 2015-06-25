package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
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
import com.sacarona.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired private OrderDAO orderDAO;
	@Autowired private CountryDAO countryDAO;
	@Autowired private ProvinceDAO provinceDAO;
	@Autowired private CityDAO cityDAO;

	@Transactional
	public Order insert(Order order) {
		order.setCreateDate(new Date());
		order.setOrderStatus(OrderStatus.OPEN);
		fillCountryAndProvince(order);
		return orderDAO.insert(order);
	}

	@Transactional
	public Order update(Order order, Long id) {
		orderDAO.update(order, id);
		fillCountryAndProvince(order);
		return order;
	}
	

	private void fillCountryAndProvince (Order order) {
		City city = cityDAO.findById(City.class, order.getCityDestiny().getId());
		Country country = countryDAO.findByIsoCode(city.getCountryIso());
		Province province = provinceDAO.findByAbbreviationAndCountry(city.getProvinceAbbreviation(), country.getExternalId());
		order.setCountryDestiny(country);
		order.setProvinceDestiny(province);
	}

	@Transactional
	public void remove(Long orderId) {
		orderDAO.remove(orderDAO.findById(Order.class, orderId));
	}

	@Transactional
	public ServiceCollectionResponse<Order> findOrdersByUser(ServiceRequest<Order> request) throws BusinessException {
		try {
			return orderDAO.findOrdersByUser(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}
	
	@Transactional
	public ServiceCollectionResponse<Order> findOrders(SearchOrdersRequest request) throws BusinessException {
		try {
			return orderDAO.findOrders(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

}
