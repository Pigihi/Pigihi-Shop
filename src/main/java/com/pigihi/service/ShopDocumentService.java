package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.repository.ShopRepository;

@Service
public class ShopDocumentService {
	
	@Autowired
	private ShopRepository shopRepository;

	public ShopEntity changeDocument(String email, String documentUrl) {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setDocumentUrl(documentUrl);
		// Change verified status of shop to false
		//TODO Send to queue or something
		shop.setVerifyStatus(false);
		ShopEntity modifiedShop = shopRepository.save(shop);
		return modifiedShop;
	}

}
