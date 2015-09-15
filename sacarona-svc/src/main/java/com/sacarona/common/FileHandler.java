package com.sacarona.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sacarona.common.svc.exception.BusinessException;

@Component
public class FileHandler {

	public File saveIntoFile (MultipartFile multipartFile) throws BusinessException {
		String fileName = "/tmp/" + generateUniqueFileName(multipartFile.getOriginalFilename().split ("\\.")[1]);
				 
		try {
			try (OutputStream stream = new FileOutputStream(fileName)) {
			    stream.write(multipartFile.getBytes());
			}
			return new File(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("Erro ");
		}
	}
	
	public String generateUniqueFileName (String extension) {
		String base = UUID.randomUUID().toString().substring(0, 12);
		StringBuilder sb = new StringBuilder();
		byte [] bytes = base.getBytes();
		for  (int i=0; i< bytes.length ;i++){
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb + "." + extension;
	}
}
