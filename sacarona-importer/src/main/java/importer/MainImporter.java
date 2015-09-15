package importer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import rest.BeanJsonConverter;
import bean.City;
import bean.Country;
import bean.Province;

public class MainImporter {

	public static void main(String[] args) throws IOException {
		CountryReader countryReader = new CountryReader();
		countryReader.read();
		
		Map<String, Country> map = countryReader.getMap();
		
		List<Country> countries = new ArrayList<>();
		for (String key : map.keySet()) {
//			System.out.println(map.get(key));
			Country country = map.get(key);
			country.check();
			countries.add(country);
		}
		
		ProvinceReader provinceReader = new ProvinceReader();
		provinceReader.read();
		
		Map<String, Province> mapProvince = provinceReader.getMap();
		List<Province> provinces = new ArrayList<>();
		for (String key : mapProvince.keySet()) {
//			System.out.println(key);
//			System.out.println(mapProvince.get(key));
			provinces.add(mapProvince.get(key));
		}
		
		System.out.println("Paises " + map.size());
		System.out.println("Estados " + mapProvince.size());
		
		BeanJsonConverter jsonConverter = new BeanJsonConverter();
		String countriesJson = jsonConverter.convertToString(countries);
		sendToServer(countriesJson, "country/importAll");
		
		String provinceJson = jsonConverter.convertToString(provinces);
		sendToServer(provinceJson, "province/importAll");
		
		
		CityImporter cityImporter = new CityImporter();
		Map<String, String> mapCitiesProvince = cityImporter.readMapProvince();
		
		for(String key : mapCitiesProvince.keySet()) {
//			System.out.println("key = " + key + ", value = " + mapCitiesProvince.get(key));
		}
		
		File file = new File ("/Users/macbookpro/projects/import-sacarona/cities/csv/");
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			System.out.println("Importando .. " + file2.getName());
			Map<String, City> mapCity = cityImporter.readCityByCountry(file2.getName(), mapCitiesProvince, mapProvince);
			List<City> cities = new ArrayList<>();
			for (String key : mapCity.keySet()) {
				City city = mapCity.get(key);
				cities.add(city);
//				System.out.println(city);
			}
			String citiesJson = jsonConverter.convertToString(cities);
			sendToServer(citiesJson, "city/importAll");
		} 
	}
	
	private static void sendToServer (String jsonContent, String urlPart) throws ClientProtocolException, IOException {
		String url = "http://52.24.61.4:9090/sacarona-svc/" + urlPart;
		HttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.addHeader("public.request.token", "739b7a0a-2b73-455b-87b2-dd511afabde5");
		post.setEntity(configureStringEntity(jsonContent));
		Long timeStart = System.currentTimeMillis();
		HttpResponse response = httpClient.execute(post);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		System.out.println(result);
		System.out.println("tempo: " +(System.currentTimeMillis() - timeStart));
	}

	private static HttpEntity configureStringEntity(String jsonContent) {
		StringEntity entity = new StringEntity(jsonContent, "UTF-8");
		entity.setContentType("application/json" + "; charset=UTF-8");
		return entity;
	}
}
