package com.sacarona.service;

import java.util.List;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.world.City;

public interface CityService {
	void insertOrUpdate (List<City> cities) throws BusinessException;
}
