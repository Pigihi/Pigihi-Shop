package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.repository.ShopRepository;

@Service
public class ShopProfileImageService {
	
	@Autowired
	private ShopRepository shopRepository;

	public ShopEntity changeProfileImage(String email, String imageUrl) {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setImageUrl(imageUrl);
		ShopEntity modifiedShop = shopRepository.save(shop);
		return modifiedShop;
	}
	
	

}
