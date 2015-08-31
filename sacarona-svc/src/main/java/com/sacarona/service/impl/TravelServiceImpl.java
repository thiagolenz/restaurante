package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.controller.request.SearchTravelersRequest;
import com.sacarona.dao.FeedbackAverageDAO;
import com.sacarona.dao.TravelDAO;
import com.sacarona.dao.UserDAO;
import com.sacarona.model.feedback.FeedbackAverage;
import com.sacarona.model.travel.Travel;
import com.sacarona.model.user.User;
import com.sacarona.service.TravelService;

@Service
public class TravelServiceImpl implements TravelService {
	@Autowired private TravelDAO travelDAO;
	@Autowired private UserDAO userDAO;
	@Autowired private FeedbackAverageDAO averageDAO;

	@Transactional
	public Travel insert(Travel travel) {
		travel.setCreateDate(new Date());
		return travelDAO.insert(travel);
	}

	@Transactional
	public Travel update(Travel travel, Long id) {
		travelDAO.update(travel, id);
		return travel;
	}

	@Transactional
	public ServiceCollectionResponse<Travel> findByUser(ServiceRequest<Travel> request) throws BusinessException {
		try {
			return travelDAO.findByUser(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}
	
	@Transactional
	public ServiceCollectionResponse<Travel> findTravelers(SearchTravelersRequest request) throws BusinessException {
		try {
			ServiceCollectionResponse<Travel> result = travelDAO.findTravelers(request);
			for (Travel travel : result.getDataList()) {
				travel.setUserName(userDAO.findById(User.class, travel.getUserId()).getName());
				FeedbackAverage average = averageDAO.findByUser(travel.getUserId());
				if (average != null)
					travel.setScore(average.getAverageValue());
			}
			return result;
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

}
