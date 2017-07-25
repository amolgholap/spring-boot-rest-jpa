package com.ag.restboot.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;



@Component
public class OauthAkanaHandler {
	private static final Log LOG = LogFactory.getLog(OauthAkanaHandler.class);
	
	 @Value("${AkanaServiceUrl}")
	 private String akanaServiceUrl;
	
	 @Value("${AkanaTokenUri}")
	 private String akanaTokenUri;
	 
	 @Value("${AkanaclientId}")
	 private String clientId;

	 @Value("${AkanaclientSecret}")
	 private String clientSecret;
	
	 @Value("${Akanascope}")
	 private String scope;
	
	
	public String  execute(String url,HttpMethod method,Object jsonObject){
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		LOG.info("Akana token  url "+akanaTokenUri );
		LOG.info("Akana token  clientId "+clientId );
		LOG.info("Akana token  scope "+scope );
        resourceDetails.setAccessTokenUri(akanaTokenUri);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setScope(Arrays.asList(scope));
        resourceDetails.setId(clientId);
        resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);

        ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
        
        OAuth2AccessToken accessToken = provider.obtainAccessToken(resourceDetails, new DefaultAccessTokenRequest());
        
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext(accessToken));

    	LOG.info("Akana api  url "+akanaServiceUrl + url);
        //final ResponseEntity<String> greeting = restTemplate.exchange(akanaServiceUrl + "fleetMapDetail/getAllSites",HttpMethod.POST,null, String.class);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_JSON);
    	
    	//restTemplate.put(url, headers);

    	org.springframework.http.HttpEntity<String> entity = new org.springframework.http.HttpEntity<String>(EPNUtility.objectToJson(jsonObject) ,headers);
    	final ResponseEntity<String> greeting=restTemplate.exchange(akanaServiceUrl + url, method, entity, String.class);
        String output=greeting.getBody();
        return output;
	}
	

}
