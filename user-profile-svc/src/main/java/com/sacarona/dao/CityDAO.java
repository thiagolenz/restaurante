package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.model.world.City;

public interface CityDAO extends GenericDAO<City> {
	City findByStateAndCode (String countryIso, String code) throws UnknownHostException;
}
