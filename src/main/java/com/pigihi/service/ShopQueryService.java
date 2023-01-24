/**
 * 
 */
package com.pigihi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.pigihi.entity.ShopEntity;
import com.pigihi.repository.ShopRepository;

/**
 * @author Ashish Sam T George
 *
 */
@Service
public class ShopQueryService implements QueryServiceInterface {
	
	@Autowired
	private ShopRepository shopRepository;

	@Override
	public ShopEntity shopInfo(String email) {
		
		ShopEntity shop = shopRepository.findByEmail(email);
		return shop;
		
	}

	@Override
	public List<ShopEntity> findShopsById(List<String> shopIds) {
		List<ShopEntity> shopEntities = (List<ShopEntity>) shopRepository.findAllById(shopIds);
		return shopEntities;
	}

}
