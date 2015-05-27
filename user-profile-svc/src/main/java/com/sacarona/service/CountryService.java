package com.sacarona.service;

import java.util.List;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.world.Country;

public interface CountryService {
	 void insertOrUpdate(List<Country> list) throws BusinessException;
}
