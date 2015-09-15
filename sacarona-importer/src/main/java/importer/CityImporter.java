package importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import bean.City;
import bean.Province;

public class CityImporter {
	public Map<String, String> readMapProvince () throws IOException {
		Map<String, String> mapCityProvince = new HashMap<String, String> ();

		File file = new File("/Users/macbookpro/projects/import-sacarona/map-cities-province.csv");
		InputStream is = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;

		while ((line = reader.readLine()) != null) {
			String parts [] = line.split(";");
			String parts1 [] = parts[0].split("\\.");
			mapCityProvince.put(parts [3], parts1 [0] + "." + parts1 [1]);
		}
		reader.close();
		is.close();

		return mapCityProvince;
	}

	public Map<String, City> readCityByCountry (String country, Map<String, String> mapCitiesProvince, 
			Map<String, Province> mapProvince) throws IOException {
		Map<String, City> mapCity = new HashMap<String, City> ();

		File file = new File("/Users/macbookpro/projects/import-sacarona/cities/csv/"+country);
		InputStream is = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;

		while ((line = reader.readLine()) != null) {
			String parts [] = line.split(";");
			String partType = parts [7];
			if (partType.startsWith("ADM")) {
				City city = new City();
				city.setImporterId(parts [0].trim());
				city.setName(parts [2].trim());
				city.setCountryIso(parts [8]);
				city.setAlternativeNames(parts [3]);
//				System.out.println(city.getName() + ":" + parts [6]);
				if (parts.length > 10) {
					city.setProvinceAbbreviation(parts [10]);
				}
				String value = mapCitiesProvince.get(city.getImporterId());
				if (value != null) {
					Province province = mapProvince.get(value);
					if (province != null)
						city.setProvinceAbbreviation(province.getAbbreviation());
				}
				mapCity.put(city.getImporterId(), city);
			}
		}
		System.out.println("COUNT = " + mapCity.size());
		reader.close();
		is.close();

		return mapCity;
	}
}	
