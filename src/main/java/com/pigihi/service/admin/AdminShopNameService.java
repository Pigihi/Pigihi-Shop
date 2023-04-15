package com.pigihi.service.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.service.ShopNameService;

@Service
public class AdminShopNameService {
	
	@Autowired
	private ShopNameService shopNameService;

	public ShopEntity changeShopName(String email, String shopName) throws IOException, InterruptedException {
		ShopEntity shop = shopNameService.changeFullName(email, shopName);
		return shop;
	}
	
}
