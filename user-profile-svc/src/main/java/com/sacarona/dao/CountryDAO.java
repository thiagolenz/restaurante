package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.model.world.Country;

public interface CountryDAO extends GenericDAO<Country> {
	Country findByExternalId (Long id) throws UnknownHostException;
}
