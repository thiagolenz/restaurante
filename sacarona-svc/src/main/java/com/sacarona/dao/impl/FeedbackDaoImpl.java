package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.FeedbackDAO;
import com.sacarona.model.feedback.Feedback;

@Repository
public class FeedbackDaoImpl extends AbstractJpaDaoImpl<Feedback> implements FeedbackDAO {
	
	@Override
	public ServiceCollectionResponse<Feedback> findByUser(ServiceRequest<Feedback> request) throws UnknownHostException {
		TypedQuery<Feedback> query = 
				em.createQuery("from Feedback o where o.userReceivedId = :userReceivedId order by o.feedbackDate DESC", Feedback.class);
		query.setParameter("userReceivedId", request.getEntity().getUserReceivedId());
		return executeQueryForPagination(query, request);
	}
}
