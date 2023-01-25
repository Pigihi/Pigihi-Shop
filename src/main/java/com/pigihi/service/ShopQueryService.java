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
 * Implementation class for shop query service interface <br>
 * Handles only query requests (no modification requests)
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class ShopQueryService implements QueryServiceInterface {
	
	@Autowired
	private ShopRepository shopRepository;

	/**
	 * Handles API request for getting information of a shop
	 * 
	 * @param email
	 * @return ShopEntity
	 * 
	 * @see ShopEntity
	 * 
	 */
	@Override
	public ShopEntity shopInfo(String email) {
		
		ShopEntity shop = shopRepository.findByEmail(email);
		return shop;
		
	}

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
	@Override
	public List<ShopEntity> findShopsById(List<String> shopIds) {
		List<ShopEntity> shopEntities = (List<ShopEntity>) shopRepository.findAllById(shopIds);
		return shopEntities;
	}

}
