package com.pigihi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.repository.ShopRepository;

@Service
public class ShopAddService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	/**
	 * Adds new shop
	 * 
	 * @param shopEntity
	 * @return ShopEntity Saved shopEntity is returned
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public ShopEntity addShop(ShopEntity shopEntity) {
		List<ShopEntity> shops = shopRepository.findByEmailAndMobile(shopEntity.getEmail(),
													shopEntity.getMobile());
		if(shops.size() < 1) {
			ShopEntity savedShop = shopRepository.save(shopEntity);
			return savedShop;
		}
		else {
			return null;
		}
	}

}
