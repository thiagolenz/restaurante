package com.sacarona.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sacarona.common.FileStorageCredentials;

@Component
public class CredentialsFactory {
	@Value("${storage.bucketname}" )
	private String bucketName;
	
	@Value("${bucket.storage.access.key}" )
	private String accessKey;
	
	@Value("${bucket.storage.secret.key}" )
	private String secretKey;
	
	public FileStorageCredentials getCredentials () {
		return new FileStorageCredentials(bucketName, accessKey, secretKey); 
	}
}
