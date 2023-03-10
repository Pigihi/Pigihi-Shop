/**
 * 
 */
package com.pigihi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pigihi.entity.ShopEntity;
import com.pigihi.entity.StatusEnum;
import com.pigihi.model.EditShopModel;
import com.pigihi.repository.ShopRepository;

/**
 * Service class for handling requests that can modify values
 * 
 * @author Ashish Sam T George
 *
 */
@Service
public class ShopService {
	
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
		ShopEntity shop = shopRepository.save(shopEntity);
		return shop;
	}
	
	/**
	 * Edits already existing shop
	 * 
	 * @param editShopModel
	 * @return ShopEntity Edited ShopEntity is returned
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public ShopEntity editShop(EditShopModel editShopModel) {
		ShopEntity shop = shopRepository.findByEmail(editShopModel.getEmail());
		shop.setOwnerFullName(editShopModel.getOwnerFullName());
		shop.setMobile(editShopModel.getMobile());
		shop.setImageUrl(editShopModel.getImageUrl());
		shop.setDocumentUrl(editShopModel.getDocumentUrl());
		shop.setStreetName(editShopModel.getStreetName());
		shop.setCityName(editShopModel.getCityName());
		shop.setPincode(editShopModel.getPincode());
		
		shop = shopRepository.save(shop);
		return shop;
	}

	/**
	 * Disables already existing shop
	 * 
	 * @param email
	 * @return ShopEntity null is returned if no shop is found
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public ShopEntity disableShop(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		if(shop != null) {
			shop.setStatus(StatusEnum.USER_DISABLED);
			shopRepository.save(shop);

			// TODO Call AuthNAuthZ microservice API to disable user
//			userLoginService.disableUser(email);
			return shop;
		}
		else {
			return null;
		}
	}

	/**
	 * Enables already existing shop
	 * 
	 * @param email
	 * @return ShopEntity null is returned if no shop is found
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	public ShopEntity enabledShop(String email) {
		ShopEntity shop = shopRepository.findByEmail(email);
		if(shop != null) {
			shop.setStatus(StatusEnum.ENABLED);
			shopRepository.save(shop);

			// TODO Call AuthNAuthZ microservice API to enable user
//			userLoginService.disableUser(email);
			return shop;
		}
		else {
			return null;
		}
	}

}
