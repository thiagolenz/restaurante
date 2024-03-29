package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CountryDAO;
import com.sacarona.model.world.Country;
import com.sacarona.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired 
	private CountryDAO countryDAO;

	@Transactional
	public void insertOrUpdate(List<Country> list) throws BusinessException {
		for (Country country : list) {
			Country existent = countryDAO.findByIsoCode(country.getIso());
			if (existent == null)
				countryDAO.insert(country);
			else 
				countryDAO.update(country, existent.getId());
		}
	}

	@Transactional
	public ServiceCollectionResponse<Country> search(ServiceRequest<Country> request) throws BusinessException {
		try {
			return countryDAO.search(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}

	@Transactional
	public Country findById(Long id) {
		return countryDAO.findById(Country.class, id);
	}

}
