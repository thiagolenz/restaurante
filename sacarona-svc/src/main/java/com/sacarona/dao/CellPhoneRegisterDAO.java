package com.sacarona.dao;

import java.net.UnknownHostException;
import java.util.List;

import com.sacarona.model.mobile.CellPhoneRegister;

public interface CellPhoneRegisterDAO extends GenericDAO<CellPhoneRegister> {
	List<CellPhoneRegister> findByUser(Long userId) throws UnknownHostException;
	
	CellPhoneRegister findExistent (CellPhoneRegister cellPhoneRegister) throws UnknownHostException;
}
