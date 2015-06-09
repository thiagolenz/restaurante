package com.sacarona.service;

import java.util.List;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.mobile.CellPhoneRegister;

public interface CellPhoneRegisterService {
	CellPhoneRegister insert (CellPhoneRegister cellPhoneRegister);
	
	List<CellPhoneRegister> findByUser (Long userId) throws BusinessException;
	
	CellPhoneRegister updateConnect (CellPhoneRegister cellPhoneRegister) throws BusinessException;
}
