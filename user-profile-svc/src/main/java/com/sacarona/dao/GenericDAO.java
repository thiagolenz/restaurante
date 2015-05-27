package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.model.AbstractEntity;

public interface GenericDAO <T extends AbstractEntity> {
	String SEQUENCE_VALUE = "sequence_value";
	T insert (T obj);

	void remove (T obj);

	void update (T obj, Long id);

	T findById(Class<T>clazz, Long id);
	
	void openBulk () throws UnknownHostException;
	
	void executeBulk () throws UnknownHostException;
}
