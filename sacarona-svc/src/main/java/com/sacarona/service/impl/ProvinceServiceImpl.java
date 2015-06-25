package com.sacarona.service.impl;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.model.world.Province;
import com.sacarona.service.ProvinceService;

@Service
public class ProvinceServiceImpl implements ProvinceService {
	@Autowired
	private ProvinceDAO provinceDAO;
	
	@Transactional
	public void insertOrUpdate(List<Province> provinces) throws BusinessException {
		try {
			for (Province province : provinces) {
				System.out.println(province);
				Province existent = provinceDAO.findByAbbreviationAndCountry(province.getAbbreviation(), province.getCountryId());
				if (existent == null)
					provinceDAO.insert(province);
				else 
					provinceDAO.update(province, existent.getId());
			}
		} catch (UnknownHostException e) {
			throw new BusinessException(e);
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
}
