package importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

import bean.Country;

public class CountryReader {
	Map<String, Country> map = new HashMap<>();
	Map<String, Country> mapName = new HashMap<>();
	
	public void read () throws IOException {
		File file = new File("/Users/macbookpro/projects/import-sacarona/countries-main.txt");
		InputStream is = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			String parts [] = line.split(",");
			Country country = new Country();
			country.setIso(parts [1]);
			country.setNameEnglish(parts [0].trim());
			country.setNameSpanish(parts[2].trim());
			map.put(country.getIso(), country);
			mapName.put(country.getNameEnglish(), country);
		}
		reader.close();
		is.close();
		
		file = new File("/Users/macbookpro/projects/import-sacarona/countries-pt-br.txt");
		is = new FileInputStream(file);
		reader = new BufferedReader(new InputStreamReader(is));
		line = null;
		while ((line = reader.readLine()) != null) {
			String parts [] = line.split(",");
			String iso = parts [0];
			Country country = map.get(iso);
			if (country != null) {
				String value = parts [3].trim();
				value =removerAcentos (value);
				country.setNamePortuguese(value);
				country.setUn(parts [1]);
			}

		}
		reader.close();
		is.close();
		
		file = new File("/Users/macbookpro/projects/import-sacarona/countries-alternatives.txt");
		is = new FileInputStream(file);
		reader = new BufferedReader(new InputStreamReader(is));
		line = null;
		while ((line = reader.readLine()) != null) {
			String parts [] = line.split(",");
			if (parts [0] != null) {
				Country country = mapName.get(parts [0]);
				if (country != null)
					country.setAlternativeNames(parts [1]);
			}
		}
		
		reader.close();
		is.close();
	}
	
	public static String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}
	
	public Map<String, Country> getMap() {
		return map;
	}
}
