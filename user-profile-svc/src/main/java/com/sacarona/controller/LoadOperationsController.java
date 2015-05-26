package com.sacarona.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/module/operations",produces=MediaType.APPLICATION_JSON_VALUE)
public class LoadOperationsController {

//	@RequestMapping(method = RequestMethod.GET)
//	@ResponseBody
//	public List<OperationServiceResponse> loadOperations () throws IOException, ClassNotFoundException {
//		Reflections reflections = new Reflections("com.redfire");
//		Set<Class<?>> annotated = 
//				reflections.getTypesAnnotatedWith(Controller.class);
//		return new LoadRestOperation().loadOperations(annotated);
//	}
}
