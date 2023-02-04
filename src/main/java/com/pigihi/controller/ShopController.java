/**
 * 
 */
package com.pigihi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.ShopEntity;
import com.pigihi.model.EditShopModel;
import com.pigihi.service.QueryServiceInterface;
import com.pigihi.service.ShopService;

/**
 * Controller class for handling shop API requests
 * 
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user/shop")
public class ShopController {
	
	@Autowired
	private QueryServiceInterface shopQueryService;
	
	@Autowired
	private ShopService shopService;
	
	/**
	 * Handles API request for getting details of shop
	 * 
	 * @param email String representing email of the shop which can uniquely identify it
	 * @return JSON string representing details of the shop
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 */
	@GetMapping
	public String shopInfo(@RequestParam String email) {
		
		ShopEntity shopEntity = shopQueryService.shopInfo(email);
		String shop = convertToJson(shopEntity);
		return shop;
		
	}
	
	/**
	 * Handles request for getting information of a list of shops
	 * 
	 * @param shopIds Array of strings representing shopIds
	 * @return JSON string
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@GetMapping("/listShops")
	public String getShopList(@RequestParam List<String> shopIds) {
		
		List<ShopEntity> shopEntities = shopQueryService.findShopsById(shopIds);
		Gson gson = new Gson();
		String shops = gson.toJson(shopEntities);
		return shops;
		
	}
	
//	@GetMapping("/listProducts")
//	public String getShopProducts(@RequestParam String email) {
//		
//		
//		
//	}
	
	/**
	 * Handles API request for adding new shops
	 * 
	 * @param shopEntity 
	 * @return JSON string containing information about the newly added shop
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
//	@PostMapping("/add")
	@PostMapping
	public String addShop(@RequestBody ShopEntity shopEntity) {
		ShopEntity savedShopEntity = shopService.addShop(shopEntity);
		String shop = convertToJson(savedShopEntity);
		return shop;
	}
	
	/**
	 * Handles API request for editing already existing shops
	 * 
	 * @param editShopModel
	 * @return JSON string containing information about the edited shop
	 * 
	 * @see EditShopModel
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
//	@PutMapping("/edit")
	@PutMapping
	public String editShop(@RequestBody EditShopModel editShopModel) {
		
		//TODO Check whether the shop already exists
		
		ShopEntity editedshop = shopService.editShop(editShopModel);
		String shop = convertToJson(editedshop);
		return shop;
	}
	
	/**
	 * Handles API request to disable already existing shop
	 * 
	 * @param email
	 * @return JSON string containing information about the disabled shop
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
//	@DeleteMapping("/disable")
	@DeleteMapping
	public String disableShop(@RequestParam String email) {
		ShopEntity disabledShop = shopService.disableShop(email);
		String shop = convertToJson(disabledShop);
		return shop;
	}
	
	/**
	 * Handles API request to enable already existing shop
	 * 
	 * @param email
	 * @return JSON string containing information about the enabled shop
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
//	@PutMapping("/enable")
	@PatchMapping
	public String enableShop(@RequestParam String email) {
		ShopEntity enabledShop = shopService.enabledShop(email);
		String shop = convertToJson(enabledShop);
		return shop;
	}
	
	private String convertToJson(ShopEntity shopEntity) {
		Gson gson = new Gson();
		String shop = gson.toJson(shopEntity);
		return shop;
	}

}
