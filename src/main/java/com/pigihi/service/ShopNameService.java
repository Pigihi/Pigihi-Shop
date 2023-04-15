package com.pigihi.service;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.repository.ShopRepository;

@Service
public class ShopNameService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private PUTRequestSender putRequestSender;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.fullName.endpoint}")
	private String fullNameEndpoint;
	
	@Value("${authService.endpoint.fullName.queryParam}")
	private String fullNameQueryParam;

	public ShopEntity changeFullName(String email, String fullName) throws InterruptedException, IOException {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setShopName(fullName);
		ShopEntity savedShop = shopRepository.save(shop);
		
		String uri = authUri.concat(fullNameEndpoint);
		HttpResponse<String> response = putRequestSender.send(uri, 
				"email", email,
				fullNameQueryParam, fullName,
				email);
		System.out.println("Response obtained from authentication service: " + response.body());
				
		return savedShop;
	}

}
