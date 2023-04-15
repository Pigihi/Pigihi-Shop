package com.pigihi.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.model.ShopAddressModel;
import com.pigihi.service.ShopAddressService;

@Service
public class AdminShopAddressService {
	
	@Autowired
	private ShopAddressService shopAddressService;

	public ShopEntity changeAddress(String email, ShopAddressModel shopAddressModel) {
		ShopEntity shop = shopAddressService.changeAddress(email, shopAddressModel);
		return shop;
	}

}
