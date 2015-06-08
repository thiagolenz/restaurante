package com.sacarona.dao.impl;

import java.math.BigDecimal;
import java.net.UnknownHostException;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.dao.FeedbackAverageDAO;
import com.sacarona.model.feedback.FeedbackAverage;

@Repository
public class FeedbackAverageDaoImpl extends AbstractDaoImpl<FeedbackAverage> implements FeedbackAverageDAO {

	@Override
	public FeedbackAverage findByUser(Long userId) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userId", userId);
		return singleQuery(query);
	}
	
	@Override
	protected String getSequenceName() {
		return "seq_feedback_average";
	}

	@Override
	protected String getCollectionName() {
		return "feedback_average";
	}

	@Override
	protected void mapObject(FeedbackAverage source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("userId", source.getUserId());
		destiny.append("averageValue", source.getAverageValue().doubleValue());
	}

	@Override
	protected FeedbackAverage mapResult(BasicDBObject source) {
		FeedbackAverage average = new FeedbackAverage();
		average.setId(source.getLong("id"));
		average.setUserId(source.getLong("userId"));
		average.setAverageValue(new BigDecimal(source.getDouble("averageValue")));
		return average;
	}

}
