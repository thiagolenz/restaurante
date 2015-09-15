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
import com.sacarona.dao.FeedbackAverageDAO;
import com.sacarona.dao.OrderAvatarDAO;
import com.sacarona.dao.OrderDAO;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.dao.UserDAO;
import com.sacarona.model.feedback.FeedbackAverage;
import com.sacarona.model.order.Order;
import com.sacarona.model.order.OrderStatus;
import com.sacarona.model.user.User;
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
	
	@Autowired private UserDAO userDAO;
	@Autowired private FeedbackAverageDAO averageDAO;
	
	@Autowired private OrderAvatarDAO avatarDAO;

	@Transactional
	public Order insert(Order order) {
		order.setCreateDate(new Date());
		order.setOrderStatus(OrderStatus.OPEN);
		return orderDAO.insert(order);
	}

	@Transactional
	public Order update(Order order, Long id) {
		orderDAO.update(order, id);
		return order;
	}
	

	public void fillCountryAndProvince (Order order) {
		City city = cityDAO.findById(City.class, order.getCityDestiny().getId());
		Country country = countryDAO.findByIsoCode(city.getCountryIso());
		Province province = provinceDAO.findByAbbreviationAndCountry(city.getProvinceAbbreviation(), country.getExternalId());
		order.setCountryDestiny(country);
		order.setProvinceDestiny(province);
	}

	@Transactional
	public void remove(Long orderId) {
		Order order = orderDAO.findById(Order.class, orderId);
		order.setRemoved(true);
		orderDAO.update(order, orderId);
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
			ServiceCollectionResponse<Order> result = orderDAO.findOrders(request);
			for (Order order : result.getDataList()) {
				order.setUserName(userDAO.findById(User.class, order.getUserId()).getName());
				FeedbackAverage average = averageDAO.findByUser(order.getUserId());
				if (average != null)
					order.setScore(average.getAverageValue());
			}
			return result;
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

}
