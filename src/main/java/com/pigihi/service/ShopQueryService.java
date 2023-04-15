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
 * Service class for shop query operations <br>
 * Handles only query requests (no modification requests)
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class ShopQueryService {
	
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
	public ShopEntity shopInfo(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		return shop;
	}

}
