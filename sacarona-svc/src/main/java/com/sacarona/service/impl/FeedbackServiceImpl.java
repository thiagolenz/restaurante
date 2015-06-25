package com.sacarona.service.impl;

import java.math.BigDecimal;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.FeedbackAverageDAO;
import com.sacarona.dao.FeedbackDAO;
import com.sacarona.model.feedback.Feedback;
import com.sacarona.model.feedback.FeedbackAverage;
import com.sacarona.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired private FeedbackDAO feedbackDAO;
	@Autowired private FeedbackAverageDAO averageDAO;

	@Transactional
	public Feedback insert(Feedback feedback) throws BusinessException {
		feedbackDAO.insert(feedback);
		try {
			updateAverageScore(feedback);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
		return feedback;
	}

	@Transactional
	public Feedback update(Feedback feedback, Long id) throws BusinessException {
		feedbackDAO.update(feedback, id);
		try {
			updateAverageScore(feedback);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
		return feedback;
	}
	
	@Transactional
	private void updateAverageScore (Feedback feedback) throws BusinessException, UnknownHostException {
		ServiceRequest<Feedback> request = new ServiceRequest<>();
		request.setRecordsRange(10);
		request.setEntity(feedback);
		ServiceCollectionResponse<Feedback> result = findByUser(request);
		BigDecimal total = BigDecimal.ZERO;
		for (Feedback item : result.getDataList()) {
			total = total.add(item.getScore());
		}
		FeedbackAverage average = averageDAO.findByUser(feedback.getUserReceivedId());
		if (average == null) {
			average = new FeedbackAverage();
			average.setUserId(feedback.getUserReceivedId());
		}
		average.setAverageValue(total.divide(new BigDecimal(result.getDataList().size())));
		if (average.getId() == null)
			averageDAO.insert(average);
		else
			averageDAO.update(average, average.getId());
	}
	
	@Transactional
	public FeedbackAverage findAverageByUser(Long userId) throws BusinessException {
		try {
			return averageDAO.findByUser(userId);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

	@Transactional
	public ServiceCollectionResponse<Feedback> findByUser(ServiceRequest<Feedback> request) throws BusinessException{
		try {
			return feedbackDAO.findByUser(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}
	
	

}
