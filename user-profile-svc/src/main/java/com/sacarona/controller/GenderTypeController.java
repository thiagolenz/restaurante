package com.sacarona.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/genderType",produces=MediaType.APPLICATION_JSON_VALUE)

public class GenderTypeController {
//	@Autowired
//	private MessageBundleService messageBundleService;
//
//	@RequestMapping(value = "/retrieveTypes", method = RequestMethod.GET)
//	public @ResponseBody List<EnumSerializableBean> retrieveStatus () throws IOException {
//		List<EnumSerializableBean> list = new ArrayList<>();
//		for (GenderType type : GenderType.values()) {
//			list.add(new EnumSerializableBean(type, 
//											  type.getValue(), 
//											  messageBundleService.translate(type.getDescription())));
//		}
//		return list;
//	}
}
