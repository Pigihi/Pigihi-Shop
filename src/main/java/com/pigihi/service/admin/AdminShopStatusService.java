package com.pigihi.service.admin;

import java.io.IOException;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.entity.StatusEnum;
import com.pigihi.repository.ShopRepository;
import com.pigihi.service.DELETERequestSender;
import com.pigihi.service.PATCHRequestSender;
import com.pigihi.service.ShopStatusService;

@Service
public class AdminShopStatusService {
	
	@Autowired
	private ShopStatusService shopStatusService;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private PATCHRequestSender patchRequestSender;
	
	@Autowired
	private DELETERequestSender deleteRequestSender;
	
	@Value("${authService.uri}")
	private String authUri;
	
	@Value("${authService.endpoint.disableUser.endpoint}")
	private String disableUserEndpoint;
	
	@Value("${authService.endpoint.disableUser.queryParam}")
	private String disableUserQueryParam;
	
	@Value("${authService.endpoint.enableUser.endpoint}")
	private String enableUserEndpoint;
	
	@Value("${authService.endpoint.enableUser.queryParam}")
	private String enableUserQueryParam;

	public ShopEntity enableShopByAdmin(String email) throws IOException, InterruptedException {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setStatus(StatusEnum.ENABLED);
		ShopEntity enabledShop = shopRepository.save(shop);
		String uri = authUri.concat(enableUserEndpoint);
		HttpResponse<String> response = patchRequestSender.send(uri, enableUserQueryParam, 
																email);
		System.out.println("Response from authentication microservice: " + response.body());
		return enabledShop;
	}

	public ShopEntity disableShopByAdmin(String email) throws IOException, InterruptedException {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setStatus(StatusEnum.ADMIN_DISABLED);
		ShopEntity disabledShop = shopRepository.save(shop);
		String uri = authUri.concat(disableUserEndpoint);
		HttpResponse<String> response = deleteRequestSender.send(uri, disableUserQueryParam, 
				email, "");
		System.out.println("Response from authentication microservice: " + response.body());
		return disabledShop;
	}

}
