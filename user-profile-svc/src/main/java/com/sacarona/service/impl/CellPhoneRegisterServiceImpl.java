package com.sacarona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.dao.CellPhoneRegisterDAO;
import com.sacarona.model.mobile.CellPhoneRegister;
import com.sacarona.service.CellPhoneRegisterService;

@Service
public class CellPhoneRegisterServiceImpl implements CellPhoneRegisterService {
	@Autowired
	private CellPhoneRegisterDAO registerDAO;
	
	public void insert(CellPhoneRegister cellPhoneRegister) {
		//TODO VERIFICAR SE JÁ EXISTE O REG ID 
		registerDAO.insert(cellPhoneRegister);
	}
}
