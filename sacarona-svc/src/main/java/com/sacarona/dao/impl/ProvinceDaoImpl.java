package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

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
	public Province findByAbbreviationAndCountry(String abbreviation, Long id) {
		TypedQuery<Province> query = em.createQuery("from Province o where o.countryId = :countryId and o.abbreviation = :abbreviation ", Province.class);
		query.setParameter("countryId", id);
		query.setParameter("abbreviation", abbreviation);
		return singleQuery(query);
	}
	
	@Override
	public Province findByAbbreviationAndCountryIso(String abbreviation, String countryIso) {
		TypedQuery<Province> query = em.createQuery("from Province o where o.countryIso = :countryIso and o.abbreviation = :abbreviation ", Province.class);
		query.setParameter("countryIso", countryIso);
		query.setParameter("abbreviation", abbreviation);
		return singleQuery(query);
	}

	@Override
	public ServiceCollectionResponse<Province> search(ServiceRequest<Province> request) throws UnknownHostException {
		Province province = request.getEntity();
		Map<String, Object> parameters = new HashMap<String, Object>();
		StringBuilder builder = new StringBuilder("from Province o where lower(o.name) like :name ");
		parameters.put("name", province.getName().toLowerCase() + "%");
		
		if (province.getCountryId() != null) {
			builder.append(" and o.countryId = :countryId");
			parameters.put("countryId", province.getCountryId());
		}
		builder.append(" order by o.name ASC");
		
		TypedQuery<Province> query = createQueryAndSetParams(builder, parameters, Province.class);

		ServiceCollectionResponse<Province> result = executeQueryForPagination(query, request);
		for (Province provinceTemp : result.getDataList()) {
			provinceTemp.setCountry(countryDAO.findByExternalId(provinceTemp.getCountryId()));
		}
		return result;
	}
}
