package com.sacarona.controller.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sacarona.common.response.Response;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.world.City;
import com.sacarona.service.CityService;

@Controller
@RequestMapping(value="/cityimporter/",produces=MediaType.APPLICATION_JSON_VALUE)
public class CityImporterController {
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryImporterController countryImporterController;

	@RequestMapping(value="/import", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Response importStates () throws IOException, BusinessException {
		cityService.insertOrUpdate(importCities(1));
		cityService.insertOrUpdate(importCities(2));
		cityService.insertOrUpdate(importCities(3));
		return Response.newSuccessResponse();
	}
	
	public List<City> importCities (int part) throws IOException {
		return readFile(part);
	}
	
	
	private List<City> readFile (int part) throws IOException {
		List<City> list = new ArrayList<>();
		InputStream is = CountryImporterController.class.getClassLoader().getResourceAsStream("data/CityList"+part+".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String [] parts = line.split(",");
			if (parts.length >= 7 ) {
				City city = new City();
				city.setCountryIso(parts[1].trim().replaceAll("\"", ""));
				city.setCode(parts[2].trim().replaceAll("\"", ""));
				city.setName(parts[3].trim().replaceAll("\"", ""));
				city.setProvinceAbbreviation(parts[5].trim().replaceAll("\"", ""));
				list.add(city);
			}
		}
		return list;
	}
}
