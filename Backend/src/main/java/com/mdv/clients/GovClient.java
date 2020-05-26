package com.mdv.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.mdv.model.User;
import com.mdv.model.UserDocuments;

@Service
public class GovClient {

	private static Logger log = LoggerFactory.getLogger(GovClient.class);

	private String url = "http://127.0.0.1:4000";
	private RestTemplate restReq;
	private HttpHeaders httpHeaders;

	public GovClient() {
		this.restReq = new RestTemplate();
		this.url = "http://127.0.0.1:4000/electeur-authentication";
		this.httpHeaders = new HttpHeaders();
		this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}

	public String sendGetUser(User user) {
		log.info("Call gov sendGetUser() for user: " + user.getName());
		UserDocuments userDoc = new UserDocuments(user.getNationalCardId(), user.getSecurityCardId());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

		String userDocJson = "";
		try {
			userDocJson = objectMapper.writeValueAsString(userDoc);
		} catch (JsonProcessingException e) {
			// Treat as successful but print the log
			e.printStackTrace();
		} catch (ResourceAccessException ce) {
			// Treat as successful but print the log
			ce.printStackTrace();
		}
		log.info("Call gov sendGetUser() request: " + userDocJson);
		HttpEntity<String> requestEntity = new HttpEntity<String>(userDocJson.toString(), httpHeaders);

		ResponseEntity<String> responseEntity = restReq.postForEntity(url, requestEntity, String.class);
		log.info("Call gov sendGetUser() status: " + responseEntity.getStatusCode() + " reponse: "
				+ responseEntity.getBody());
		return responseEntity.getBody();
	}
}
