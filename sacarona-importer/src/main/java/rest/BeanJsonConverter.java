package rest;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;

public class BeanJsonConverter {
	public String convertToString (Object bean) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			StringWriter stringEmp = new StringWriter();
			objectMapper.writeValue(stringEmp, bean);
			return stringEmp.toString();
		} catch (IOException e1) {
			e1.printStackTrace();
			return "Internal Error Server";
		}
	}
	
	public Object convertToObject (String jsonString, Class<?> clazz) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonString, clazz);
	}
}
