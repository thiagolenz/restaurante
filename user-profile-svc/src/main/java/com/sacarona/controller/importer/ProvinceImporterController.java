package com.sacarona.controller.importer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.sacarona.common.response.Response;
import com.sacarona.common.svc.exception.BusinessException;
import com.sacarona.model.world.Province;
import com.sacarona.service.ProvinceService;

@Controller
@RequestMapping(value="/provinceimporter/",produces=MediaType.APPLICATION_JSON_VALUE)
public class ProvinceImporterController {
	
	@Autowired
	private ProvinceService provinceService;

	@RequestMapping(value="/import", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Response importStates () throws IOException, BusinessException {
		List<Province> result = readFileProvince();
		provinceService.insertOrUpdate(result);
		return Response.newSuccessResponse();
	}
	
	private List<Province> readFileProvince () {
		List<Province> result = new ArrayList<>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(ProvinceImporterController.class.getClassLoader().getResourceAsStream("data/province.xml"));

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("state_province");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Province province = new Province();
					province.setCountryId(Long.valueOf(eElement.getElementsByTagName("country_id").item(0).getTextContent()));
					province.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
					province.setAbbreviation(eElement.getElementsByTagName("abbreviation").item(0).getTextContent());

					result.add(province);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
