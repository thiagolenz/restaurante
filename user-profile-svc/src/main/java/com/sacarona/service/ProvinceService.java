package com.sacarona.service;

import java.util.List;

import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.world.Province;

public interface ProvinceService {
	void insertOrUpdate (List<Province> provinces) throws BusinessException;
}
