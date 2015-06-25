package com.sacarona.dao.impl;

import java.math.BigDecimal;
import java.net.UnknownHostException;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.FeedbackDAO;
import com.sacarona.model.feedback.Feedback;

@Repository
public class FeedbackDaoImpl extends AbstractJpaDaoImpl<Feedback> implements FeedbackDAO {
	
	@Override
	public ServiceCollectionResponse<Feedback> findByUser(ServiceRequest<Feedback> request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userReceivedId", request.getEntity().getUserReceivedId());
		BasicDBObject orderBy = new BasicDBObject("feedbackDate", -1);
		return executeQueryPatination(request, query, orderBy);
	}
}
