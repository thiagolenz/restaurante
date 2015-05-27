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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sacarona.model.world.Country;


public class ReadXmlFile {

	public static void main(String argv[]) throws IOException {
		Map<String, String> map = new ReadXmlFile().mapCountriesCode();
		System.out.println(map.keySet().size());
		List<Country> result = new ReadXmlFile().readFile(map);
		System.out.println(result.size());
		for (Country country : result) {
			System.out.println(country);
		}
	}

	private Map<String, String> mapCountriesCode () throws IOException {
		Map<String, String> map = new HashMap<>();
		InputStream is = ReadXmlFile.class.getClassLoader().getResourceAsStream("country-codes.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = reader.readLine()) != null) {
			String [] parts = line.split(",");
			map.put(parts[0], parts[1]);
		}
		return map;
	}

	private List<Country> readFile (Map<String, String> mapCodes) {
		List<Country> result = new ArrayList<>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(ReadXmlFile.class.getClassLoader().getResourceAsStream("country.xml"));

			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("country");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Country country = new Country();
					country.setId(Long.valueOf(eElement.getElementsByTagName("country_id").item(0).getTextContent()));
					country.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					country.setIso(eElement.getElementsByTagName("iso_code").item(0).getTextContent());
					country.setUn(mapCodes.get(country.getIso()));
					if (country.getUn() == null)
						country.setUn(country.getIso());
					result.add(country);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}