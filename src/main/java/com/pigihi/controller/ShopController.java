/**
 * 
 */
package com.pigihi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.ShopEntity;
import com.pigihi.service.QueryServiceInterface;
import com.pigihi.service.ShopQueryService;

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
	
	@GetMapping
	public String shopInfo(@RequestParam String email) {
		
		ShopEntity shopEntity = shopQueryService.shopInfo(email);
		String shop = convertToJson(shopEntity);
		return shop;
		
	}
	
	
	
	private String convertToJson(ShopEntity shopEntity) {
		Gson gson = new Gson();
		String shop = gson.toJson(shopEntity);
		return shop;
	}

}
