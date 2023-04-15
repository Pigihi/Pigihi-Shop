package com.pigihi.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.service.ShopProfileImageService;

@Service
public class AdminShopProfileImageService {
	
	@Autowired
	private ShopProfileImageService shopProfileImageService;

	public ShopEntity changeProfileImage(String email, String imageUrl) {
		ShopEntity shop = shopProfileImageService.changeProfileImage(email, imageUrl);
		return shop;
	}

}
