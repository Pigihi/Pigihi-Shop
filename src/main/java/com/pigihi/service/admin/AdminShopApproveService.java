package com.pigihi.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.repository.ShopRepository;

@Service
public class AdminShopApproveService {
	
	@Autowired
	private ShopRepository shopRepository;

	public ShopEntity approveShop(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setVerifyStatus(true);
		ShopEntity modifiedShop = shopRepository.save(shop);
		return modifiedShop;
	}

	public ShopEntity disapproveShop(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		shop.setVerifyStatus(false);
		ShopEntity modifiedShop = shopRepository.save(shop);
		return modifiedShop;
	}

}
