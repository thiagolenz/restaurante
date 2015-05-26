package com.sacarona.dao;

import java.net.UnknownHostException;

import com.sacarona.model.User;

public interface UserDAO extends GenericDAO<User> {
	User findBySocialMediaAndEmail (User user) throws UnknownHostException;
}
