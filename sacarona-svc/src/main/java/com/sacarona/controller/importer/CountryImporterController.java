package com.sacarona.controller.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;
import com.sacarona.common.response.Response;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.world.Country;
import com.sacarona.service.CountryService;


@Controller
@RequestMapping(value="/countryimporter/",produces=MediaType.APPLICATION_JSON_VALUE)
public class CountryImporterController {
	@Autowired
	private CountryService countryService;
	
	@RequestMapping(value="/import", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Response importCountries () throws IOException, BusinessException {
		Map<String, String> map = mapCountriesCode();
		Map<String, String> mapPort = mapPortuguese();
		Map<String, String> mapSpan = mapSpanish();
		List<Country> result = readFile(map, mapPort, mapSpan);
		countryService.insertOrUpdate(result);
		return Response.newSuccessResponse();
	}
	
	private Map<String, String> mapCountriesCode () throws IOException {
		Map<String, String> map = new HashMap<>();
		InputStream is = CountryImporterController.class.getClassLoader().getResourceAsStream("data/country-codes.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String [] parts = line.split(",");
			map.put(parts[0], parts[1]);
		}
		return map;
	}
	
	private Map<String, String> mapPortuguese () throws IOException {
		Map<String, String> map = new HashMap<>();
		InputStream is = CountryImporterController.class.getClassLoader().getResourceAsStream("data/countries-pt-br.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String part1 = line.substring(4, 7);
			System.out.println(part1);
			String part2 = line.substring(14, line.length());
			System.out.println(part2);
			map.put(part1, part2);
		}
		return map;
	}
	
	private Map<String, String> mapSpanish() throws IOException {
		Map<String, String> map = new HashMap<>();
		InputStream is = CountryImporterController.class.getClassLoader().getResourceAsStream("data/countries-es.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String [] parts = line.split(",");
			String part1 = parts [1];
			part1 = part1.substring(1, part1.length());
			map.put(part1, parts[2].trim());
		}
		return map;
	}

	private List<Country> readFile (Map<String, String> mapCodes, Map<String, String> mapPort, Map<String, String> mapSpanish) {
		List<Country> result = new ArrayList<>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(CountryImporterController.class.getClassLoader().getResourceAsStream("data/country.xml"));

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("country");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Country country = new Country();
					country.setExternalId(Long.valueOf(eElement.getElementsByTagName("country_id").item(0).getTextContent()));
					country.setNameEnglish(eElement.getElementsByTagName("name").item(0).getTextContent());
					country.setIso(eElement.getElementsByTagName("iso_code").item(0).getTextContent());
					country.setUn(mapCodes.get(country.getIso()));
					if (country.getUn() == null)
						country.setUn(country.getIso());
					country.setNamePortuguese(mapPort.get(country.getUn()));
					country.setNameSpanish(mapSpanish.get(country.getIso()));
					if (Strings.isNullOrEmpty(country.getNameSpanish()))
						country.setNameSpanish(country.getNameEnglish());
					if (Strings.isNullOrEmpty(country.getNamePortuguese()))
						country.setNamePortuguese(country.getNameEnglish());
					result.add(country);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		new CountryImporterController().mapPortuguese();
	}
}
