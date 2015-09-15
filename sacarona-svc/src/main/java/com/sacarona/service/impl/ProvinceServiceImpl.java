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
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.model.world.Country;
import com.sacarona.model.world.Province;
import com.sacarona.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceDAO provinceDAO;
	
	@Autowired
	private CountryDAO countryDAO;

	@Transactional
	public void insertOrUpdate(List<Province> provinces) throws BusinessException {
		for (Province province : provinces) {
			Province existent = provinceDAO.findByAbbreviationAndCountryIso(province.getAbbreviation(), province.getCountryIso());
			Country country = countryDAO.findByIsoCode(province.getCountryIso());
			if (country != null) {
				if (existent == null) {
					province.setCountryId(country.getId());
					provinceDAO.insert(province);
				} else { 
					province.setCountryId(country.getId());
					provinceDAO.update(province, existent.getId());
				}
			}
		}
	}

	@Transactional
	public ServiceCollectionResponse<Province> search(ServiceRequest<Province> request) throws BusinessException {
		try {
			return provinceDAO.search(request);
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
		}
	}
	
	@Transactional
	public Province findById(Long id) {
		return provinceDAO.findById(Province.class, id);
	}
}
