package com.sacarona.dao.impl;

import java.net.UnknownHostException;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CountryDAO;
import com.sacarona.dao.ProvinceDAO;
import com.sacarona.model.world.Province;

@Repository
public class ProvinceDaoImpl extends AbstractJpaDaoImpl<Province> implements ProvinceDAO {
	@Autowired
	private CountryDAO countryDAO;
	
	@Override
	public Province findByAbbreviationAndCountry(String abbreviation, Long id) throws UnknownHostException {
		TypedQuery<Province> query = em.createQuery("from Province o where o.countryId = :countryId and o.abbreviation = :abbreviation ", Province.class);
		query.setParameter("countryId", id);
		query.setParameter("abbreviation", abbreviation);
		return singleQuery(query);
	}

	@Override
	public ServiceCollectionResponse<Province> search(ServiceRequest<Province> request) throws UnknownHostException {
		Province province = request.getEntity();
		TypedQuery<Province> query = em.createQuery("from Province o where o.name like :name ", Province.class);
		query.setParameter("name", province.getName() + "%");

		ServiceCollectionResponse<Province> result = executeQueryForPagination(query, request);
		for (Province provinceTemp : result.getDataList()) {
			provinceTemp.setCountry(countryDAO.findByExternalId(provinceTemp.getCountryId()));
		}
		return result;
	}
}
