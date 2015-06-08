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
public class FeedbackDaoImpl extends AbstractDaoImpl<Feedback> implements FeedbackDAO {
	
	@Override
	public ServiceCollectionResponse<Feedback> findByUser(ServiceRequest<Feedback> request) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userReceivedId", request.getEntity().getUserReceivedId());
		BasicDBObject orderBy = new BasicDBObject("feedbackDate", -1);
		return executeQueryPatination(request, query, orderBy);
	}

	@Override
	protected String getSequenceName() {
		return "seq_feedback";
	}

	@Override
	protected String getCollectionName() {
		return "feedback";
	}

	@Override
	protected void mapObject(Feedback source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("userGaveId", source.getUserGaveId());
		destiny.append("userReceivedId", source.getUserReceivedId());
		destiny.append("score", source.getScore().doubleValue());
		destiny.append("message", source.getMessage());
		destiny.append("feedbackDate", source.getFeedbackDate());
	}

	@Override
	protected Feedback mapResult(BasicDBObject source) {
		Feedback feedback = new Feedback();
		feedback.setId(source.getLong("id"));
		feedback.setUserGaveId(source.getLong("userGaveId"));
		feedback.setUserReceivedId(source.getLong("userReceivedId"));
		feedback.setScore(new BigDecimal (source.getDouble("score")));
		feedback.setMessage(source.getString("message"));
		feedback.setFeedbackDate(source.getDate("feedbackDate"));
		return feedback;
	}

}
