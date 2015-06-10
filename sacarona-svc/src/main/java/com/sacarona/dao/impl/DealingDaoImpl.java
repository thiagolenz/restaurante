package com.sacarona.dao.impl;

import java.math.BigDecimal;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.DealingDAO;
import com.sacarona.dao.OrderDAO;
import com.sacarona.dao.TravelDAO;
import com.sacarona.dao.UserDAO;
import com.sacarona.model.dealing.Dealing;
import com.sacarona.model.order.Order;
import com.sacarona.model.travel.Travel;
import com.sacarona.model.user.User;

@Repository
public class DealingDaoImpl extends AbstractDaoImpl<Dealing> implements DealingDAO {
	
	@Autowired private UserDAO userDAO;
	@Autowired private TravelDAO travelDAO;
	@Autowired private OrderDAO orderDAO;
	
	@Override
	public ServiceCollectionResponse<Dealing> findByUser(ServiceRequest<Dealing> request) throws UnknownHostException {
		Dealing entity = request.getEntity();
		DBObject clause1 = new BasicDBObject("requesterUserId", entity.getTravelerUser().getId());  
		DBObject clause2 = new BasicDBObject("travelerUserId",  entity.getTravelerUser().getId());    
		BasicDBList or = new BasicDBList();
		or.add(clause1);
		or.add(clause2);
		BasicDBObject query = new BasicDBObject("$or", or);
		BasicDBObject orderBy = new BasicDBObject("createDate", -1);
		ServiceCollectionResponse<Dealing> response = executeQueryPatination(request, query, orderBy);
		for (Dealing dealing : response.getDataList()) {
			fillData(dealing);
		}
		return response;
	}

	@Override
	protected String getSequenceName() {
		return "seq_dealing";
	}

	@Override
	protected String getCollectionName() {
		return "dealing";
	}

	@Override
	protected void mapObject(Dealing source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("travelId", source.getTravel().getId());
		destiny.append("orderId", source.getOrder().getId());
		destiny.append("requesterUserId", source.getOrderUser().getId());
		destiny.append("travelerUserId", source.getTravelerUser().getId());
		destiny.append("requesterPaid", source.isRequesterPaid());
		destiny.append("travelerPaid", source.isTravelerPaid());
		destiny.append("amountTravelerPaid", source.getAmountTravelerPaid().doubleValue());
		destiny.append("amountRequesterPaid", source.getAmountRequesterPaid().doubleValue());
		destiny.append("createDate", source.getCreateDate());
	}

	@Override
	protected Dealing mapResult(BasicDBObject source) {
		Dealing dealing = new Dealing();
		dealing.setId(source.getLong("id"));
		dealing.setTravel(new Travel (source.getLong("travelId")));
		dealing.setOrder(new Order (source.getLong("orderId")));
		dealing.setOrderUser(new User (source.getLong("requesterUserId")));
		dealing.setTravelerUser(new User (source.getLong("travelerUserId")));
		dealing.setRequesterPaid(source.getBoolean("requesterPaid"));
		dealing.setTravelerPaid(source.getBoolean("travelerPaid"));
		dealing.setAmountRequesterPaid(new BigDecimal(source.getDouble("amountTravelerPaid")));
		dealing.setAmountTravelerPaid(new BigDecimal(source.getDouble("amountRequesterPaid")));
		dealing.setCreateDate(source.getDate("createDate"));
		return dealing;
	}
	
	private void fillData (Dealing dealing) {
		dealing.setOrder(orderDAO.findById(Order.class, dealing.getOrder().getId()));
		dealing.setTravel(travelDAO.findById(Travel.class, dealing.getTravel().getId()));
		dealing.setTravelerUser(userDAO.findById(User.class, dealing.getTravelerUser().getId()));
		dealing.setOrderUser(userDAO.findById(User.class, dealing.getOrderUser().getId()));
	}

}
