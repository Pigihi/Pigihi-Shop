package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.entity.StatusEnum;
import com.pigihi.repository.ShopRepository;

@Service
public class ShopStatusService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private DELETERequestSender deleteRequestSender;
	
	@Autowired
	private PATCHRequestSender patchRequestSender;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.enableUserOwn.endpoint}")
	private String enableUserEndpoint;
	
	@Value("${authService.endpoint.enableUserOwn.queryParam}")
	private String enableUserQueryParam;

	@Value("${authService.endpoint.disableUserOwn.endpoint}")
	private String disableUserEndpoint;
	
	@Value("${authService.endpoint.disableUserOwn.queryParam}")
	private String disableUserQueryParam;
	
	/**
	 * Disables already existing shop
	 * 
	 * @param email
	 * @return ShopEntity null is returned if no shop is found
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public ShopEntity disableShop(String email) throws InterruptedException, IOException {
		ShopEntity shop = shopRepository.findByEmail(email);
		if(shop != null && shop.getStatus() != StatusEnum.ADMIN_DISABLED) {
			shop.setStatus(StatusEnum.USER_DISABLED);
			ShopEntity disabledShop = shopRepository.save(shop);

			//TODO Check the response
			
			String uri = authUri.concat(disableUserEndpoint);
			HttpResponse<String> response = deleteRequestSender.send(uri, disableUserQueryParam, email, email);
			System.out.println("Response from authentication microservice: " + response.body());
			
			return shop;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Enables already existing shop
	 * 
	 * @param email
	 * @return ShopEntity null is returned if no shop is found
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public ShopEntity enableShop(String email) throws InterruptedException, IOException {
		ShopEntity shop = shopRepository.findByEmail(email);
		if(shop != null && shop.getStatus() != StatusEnum.ADMIN_DISABLED) {
			shop.setStatus(StatusEnum.ENABLED);
			ShopEntity enabledShop = shopRepository.save(shop);
			
			//TODO Check response
			
			String uri = authUri.concat(enableUserEndpoint);
			HttpResponse<String> response = patchRequestSender.send(uri, enableUserQueryParam, 
																	email, email);
			System.out.println("Response from authentication microservice: " + response.body());
			return enabledShop;
		}
		else {
			return null;
		}
	}

}
