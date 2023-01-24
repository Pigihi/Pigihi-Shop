/**
 * 
 */
package com.pigihi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping
	public String shopInfo(@RequestParam String email) {
		
		ShopEntity shopEntity = shopQueryService.shopInfo(email);
		String shop = convertToJson(shopEntity);
		return shop;
		
	}
	
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
	
	@PostMapping("/add")
	public String addShop(@RequestBody ShopEntity shopEntity) {
		ShopEntity savedShopEntity = shopService.addShop(shopEntity);
		String shop = convertToJson(savedShopEntity);
		return shop;
	}
	
	@PutMapping("/edit")
	public String editShop(@RequestBody EditShopModel editShopModel) {
		ShopEntity editedshop = shopService.editShop(editShopModel);
		String shop = convertToJson(editedshop);
		return shop;
	}
	
	@DeleteMapping("/disable")
	public String disableShop(@RequestParam String email) {
		ShopEntity disabledShop = shopService.disableShop(email);
		String shop = convertToJson(disabledShop);
		return shop;
	}
	
	@PutMapping("/enable")
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
