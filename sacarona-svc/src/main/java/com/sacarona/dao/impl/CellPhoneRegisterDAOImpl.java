package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CellPhoneRegisterDAO;
import com.sacarona.model.mobile.CellPhoneRegister;

@Repository
public class CellPhoneRegisterDAOImpl extends AbstractDaoImpl<CellPhoneRegister> implements CellPhoneRegisterDAO {
	
	@Override
	public CellPhoneRegister findExistent(CellPhoneRegister cellPhoneRegister) throws UnknownHostException {
		BasicDBObject query = new BasicDBObject();
		query.append("userId", cellPhoneRegister.getUserId());
		query.append("regId", cellPhoneRegister.getRegId());
		return singleQuery(query);
	}
	
	@Override
	public List<CellPhoneRegister> findByUser(Long userId) throws UnknownHostException {
		ServiceRequest<CellPhoneRegister> request = new ServiceRequest<>();
		BasicDBObject query = new BasicDBObject();
		query.append("userId", userId);
		ServiceCollectionResponse<CellPhoneRegister> result = executeQueryPatination(request, query);
		return result.getDataList();
	}

	@Override
	protected String getSequenceName() {
		return "seq_cell_phone_register";
	}

	@Override
	protected String getCollectionName() {
		return "cell_phone_register";
	}

	@Override
	protected void mapObject(CellPhoneRegister source, BasicDBObject destiny) {
		destiny.append("id", source.getId());
		destiny.append("userId", source.getUserId());
		destiny.append("regId", source.getRegId());
		destiny.append("type", source.getType());
	}

	@Override
	protected CellPhoneRegister mapResult(BasicDBObject source) {
		CellPhoneRegister register = new CellPhoneRegister();
		register.setId(source.getLong("id"));
		register.setRegId(source.getString("regId"));
		register.setUserId(source.getLong("userId"));
		register.setType(source.getString("type"));
		return register;
	}

}
