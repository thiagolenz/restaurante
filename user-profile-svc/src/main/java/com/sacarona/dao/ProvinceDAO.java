package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.model.world.Province;

public interface ProvinceDAO extends GenericDAO<Province> {
	Province findByAbbreviationAndCountry (String abbreviation, Long id) throws UnknownHostException;
}
