package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.model.ShopAddressModel;
import com.pigihi.repository.ShopRepository;

@Service
public class ShopAddressService {
	
	@Autowired
	private ShopRepository shopRepository;

	public ShopEntity changeAddress(String email, ShopAddressModel shopAddressModel) {
		ShopEntity shop = shopRepository.findByEmail(email);
//		shop.setShopName(shopAddressModel.getShopName());
		shop.setStreetName(shopAddressModel.getStreetName());
		shop.setCityName(shopAddressModel.getCityName());
		shop.setPincode(shopAddressModel.getPincode());
		
		ShopEntity modifiedShop = shopRepository.save(shop);
		return modifiedShop;
	}
	
	

}
