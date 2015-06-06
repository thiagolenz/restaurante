package com.sacarona.dao.impl;

import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.sacarona.dao.CellPhoneRegisterDAO;
import com.sacarona.model.mobile.CellPhoneRegister;

@Repository
public class CellPhoneRegisterDAOImpl extends AbstractDaoImpl<CellPhoneRegister> implements CellPhoneRegisterDAO {

	@Override
	protected String getSequenceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCollectionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void mapObject(CellPhoneRegister object,
			BasicDBObject objectDestiny) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected CellPhoneRegister mapResult(BasicDBObject objectDestiny) {
		// TODO Auto-generated method stub
		return null;
	}

}
