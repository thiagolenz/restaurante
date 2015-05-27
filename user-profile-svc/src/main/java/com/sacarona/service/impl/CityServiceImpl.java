package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.dao.CityDAO;
import com.sacarona.model.world.City;
import com.sacarona.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDAO cityDAO;
	
	public void insertOrUpdate(List<City> cities) throws BusinessException {
		for (City city : cities) {
			System.out.println("inserting " + city);
			City existent;
			try {
				existent = cityDAO.findByStateAndCode(city.getCountryIso(), city.getCode());
			} catch (UnknownHostException e) {
				throw new BusinessException(e);
			}
			if (existent == null)
				cityDAO.insert(city);
			else 
				cityDAO.update(city, existent.getId());
		}
	}
}
