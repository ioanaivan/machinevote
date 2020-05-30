/**
 * @package com.mdv.clients
 * @brief Clients for calling other applications
 */
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.mdv.model.User;
import com.mdv.model.UserDocuments;
import java.io.IOException;

/**
 * 
 * @brief Class to represent the simulator for the government solution
 * 
 * @author Ioana Ivan
 * @date 29/05/2020
 */
@Service
public class GovClient {

	/**
	 * @brief Logger for the GovClient class
	 */
	private static Logger log = LoggerFactory.getLogger(GovClient.class);

	/**
	 * @brief Url to call the Government server
	 */
	private String url = "";

	/**
	 * @brief Synchronous client to perform HTTP requests
	 */
	private RestTemplate restReq;

	/**
	 * @brief Headers for the request
	 */
	private HttpHeaders httpHeaders;

	/**
	 * @brief Constructor
	 */
	public GovClient() {
		this.restReq = new RestTemplate();
		this.url = "http://127.0.0.1:4000/electeur-authentication";
		this.httpHeaders = new HttpHeaders();
		this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
	}

	/**
	 * @brief Sends the Http request to check user data is present in Gov server
	 * 
	 * @author Ioana Ivan
	 * @date 29/05/2020
	 * 
	 * @param String the user to be searched
	 * @return <b>String</b> the response body from the Http response received
	 */
	public String sendGetUser(User user) {
		log.info("Call gov sendGetUser() for user: " + user.getName());
		UserDocuments userDoc = new UserDocuments(user.getNationalCardId(), user.getSecurityCardId());

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

		String userGetRequestJson = "";
		String userGetResponseJson = "";
		String responseText = "";
		try {
			userGetRequestJson = objectMapper.writeValueAsString(userDoc);

			log.info("Call gov sendGetUser() request: " + userGetRequestJson);
			HttpEntity<String> requestEntity = new HttpEntity<String>(userGetRequestJson.toString(), httpHeaders);

			// Send request to client
			ResponseEntity<String> responseEntity = restReq.postForEntity(url, requestEntity, String.class);

			userGetResponseJson = responseEntity.getBody();
			JsonNode response = objectMapper.readTree(userGetResponseJson);
			responseText = response.get("message").asText();

			log.info("Call gov sendGetUser() status: " + responseEntity.getStatusCode() + " reponse: " + responseText);

		} catch (IOException ioe) {
			// Treat as successful but print the log
			ioe.printStackTrace();
		} catch (ResourceAccessException ce) {
			// Treat as successful but print the log
			ce.printStackTrace();
		}

		return responseText;
	}
}
