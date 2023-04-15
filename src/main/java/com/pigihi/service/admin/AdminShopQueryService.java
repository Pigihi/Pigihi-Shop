package com.pigihi.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.repository.ShopRepository;

@Service
public class AdminShopQueryService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	/**
	 * Handles API request for getting information of a list of shops
	 * 
	 * @param shopIds Array of strings representing the shopIds
	 * @return List<ShopEntity>
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public List<ShopEntity> findShopsById(List<String> shopIds) {
		List<ShopEntity> shopEntities = (List<ShopEntity>) shopRepository.findAllById(shopIds);
		return shopEntities;
	}
	
	public List<ShopEntity> findAllShops(){
		List<ShopEntity> shopEntities = shopRepository.findAll();
		return shopEntities;
	}

	public ShopEntity findShop(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		return shop;
	}

}
