package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.CellPhoneRegisterDAO;
import com.sacarona.model.mobile.CellPhoneRegister;
import com.sacarona.service.CellPhoneRegisterService;

@Service
public class CellPhoneRegisterServiceImpl implements CellPhoneRegisterService {
	@Autowired
	private CellPhoneRegisterDAO registerDAO;
	
	@Transactional
	public CellPhoneRegister updateConnect(CellPhoneRegister cellPhoneRegister) throws BusinessException {
		CellPhoneRegister existent;
		try {
			existent = registerDAO.findExistent(cellPhoneRegister);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
		if (existent == null)
			return insert(cellPhoneRegister);
		else {
			existent.setLastConnected(new Date());
			existent.setType(cellPhoneRegister.getType());
			registerDAO.update(existent, existent.getId());
			return existent;
		}
	}
	
	@Transactional
	public CellPhoneRegister insert(CellPhoneRegister cellPhoneRegister) {
		cellPhoneRegister.setLastConnected(new Date());
		return registerDAO.insert(cellPhoneRegister);
	}
	
	@Transactional
	public List<CellPhoneRegister> findByUser(Long userId) throws BusinessException {
		try {
			return registerDAO.findByUser(userId);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}
	
	
}
