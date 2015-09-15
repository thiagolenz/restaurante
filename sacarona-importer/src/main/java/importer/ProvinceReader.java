package importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import bean.Province;

public class ProvinceReader {
	Map<String, Province> mapAbbrev = new HashMap<>();
	Map<String, Province> map = new HashMap<>();
	
	public void read () throws IOException {
		Map<Long, String>  countries = readCountries();
		List<Province> result = new ArrayList<>();	
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			File file = new File("/Users/macbookpro/projects/import-sacarona/province.xml");
			InputStream is = new FileInputStream(file);
			Document doc = dBuilder.parse(is);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("state_province");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Province province = new Province();
					
					Long countryId = Long.valueOf(eElement.getElementsByTagName("country_id").item(0).getTextContent());
					province.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					province.setAbbreviation(eElement.getElementsByTagName("abbreviation").item(0).getTextContent());

					result.add(province);
					String iso = countries.get(countryId);
					province.setCountryIso(iso);
					mapAbbrev.put(province.getName()+"_"+iso, province);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		File file = new File("/Users/macbookpro/projects/import-sacarona/provinces-by-country.csv");
		InputStream is = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			String parts [] = line.split(",");
			String part1 [] = parts [0].split("\\.");
			String iso = part1 [0];
			String provinceName = parts [2];
			Province p = new Province();
			p.setCountryIso(iso);
			p.setName(provinceName);
			p.setProvinceId(parts[3]);
			if (mapAbbrev.containsKey(provinceName + "_" + iso)) {
				p.setAbbreviation(mapAbbrev.get(provinceName + "_" + iso).getAbbreviation());
			} else {
				p.setAbbreviation(p.getName());
			}
			map.put(parts[0], p);
		}
		reader.close();
		is.close();
	}
	
	private Map<Long, String> readCountries () {
		Map<Long, String> map = new HashMap<>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			File file = new File("/Users/macbookpro/projects/import-sacarona/country.xml");
			InputStream is = new FileInputStream(file);
			Document doc = dBuilder.parse(is);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("country");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String iso = eElement.getElementsByTagName("iso_code").item(0).getTextContent();
					Long value = Long.valueOf(eElement.getElementsByTagName("country_id").item(0).getTextContent());
					map.put(value, iso);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public Map<String, Province> getMap() {
		return map;
	}
}
