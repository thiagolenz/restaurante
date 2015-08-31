package com.sacarona.dao.impl;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.CountryDAO;
import com.sacarona.model.world.Country;

@Repository
public class CountryDaoImpl extends AbstractJpaDaoImpl<Country> implements CountryDAO {
	@Override
	public Country findByExternalId (Long id) throws UnknownHostException {
		TypedQuery<Country> query = em.createQuery("from Country o where o.externalId = :externalId ", Country.class);
		query.setParameter("externalId", id);
		return singleQuery(query);
	}
	
	@Override
	public ServiceCollectionResponse<Country> search(ServiceRequest<Country> request) throws UnknownHostException {
		Country country = request.getEntity();
		StringBuilder builder = new StringBuilder("from Country o where ");
		Map<String, String> params = new HashMap<String, String>();
		addNameQueryParam(request, builder, country.getNameEnglish(), params);
		TypedQuery<Country> query = em.createQuery(builder.toString(), Country.class);
		query.setParameter("name", params.get("name"));
		return executeQueryForPagination(query, request);
	}

	private void addNameQueryParam(ServiceRequest<Country> request, StringBuilder builder, String name, Map<String, String> params) {
		String lang = request.getUser().getLang(); 
		params.put("name", name.toLowerCase() + "%");
		if (lang == null)
			lang = "en-US";
		if (lang.equals("en-US")) {
			builder.append(" lower(o.nameEnglish) like :name order by o.nameEnglish");
		} else if (lang.equals("es")) {
			builder.append(" lower(o.nameSpanish) like :name order by o.nameSpanish");
		} else { 
			builder.append(" lower(o.namePortuguese) like :name order by o.namePortuguese");
		}
	}
	
	@Override
	public Country findByIsoCode(String iso) {
		TypedQuery<Country> query = em.createQuery("from Country o where o.iso = :iso ", Country.class);
		query.setParameter("iso", iso);
		return singleQuery(query);
	}

}
