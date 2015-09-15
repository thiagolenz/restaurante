package com.sacarona.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sacarona.common.svc.io.ServiceCollectionResponse;
import com.sacarona.common.svc.io.ServiceRequest;
import com.sacarona.dao.LocationDAO;
import com.sacarona.model.world.Location;

@Repository
public class LocationDaoImpl extends AbstractJpaDaoImpl<Location> implements
		LocationDAO {

	@Override
	public ServiceCollectionResponse<Location> search(ServiceRequest<Location> request) {
		Location country = request.getEntity();
		StringBuilder builder = new StringBuilder("from Location o where ");
		Map<String, String> params = new HashMap<String, String>();
		addNameQueryParam(request, builder, country.getNameEnglish(), params);
		TypedQuery<Location> query = em.createQuery(builder.toString(), Location.class);
		query.setParameter("name", params.get("name"));
		ServiceCollectionResponse<Location> result = executeQueryForPagination(query, request);
		for (Location item : result.getDataList()) {
			item.setAlternativeNames(null);
		}
		return result;
	}

	private void addNameQueryParam(ServiceRequest<Location> request, StringBuilder builder, String name, Map<String, String> params) {
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
}
