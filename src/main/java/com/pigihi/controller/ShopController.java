/**
 * 
 */
package com.pigihi.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.EditShopModel;
import com.pigihi.model.ShopAddressModel;
import com.pigihi.service.ShopAddService;
import com.pigihi.service.ShopAddressService;
import com.pigihi.service.ShopDocumentService;
import com.pigihi.service.ShopNameService;
import com.pigihi.service.ShopProfileImageService;
import com.pigihi.service.ShopQueryService;
import com.pigihi.service.ShopStatusService;


/**
 * Controller class for handling shop API requests
 * 
 * @author Ashish Sam T George
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user/shop/self")
public class ShopController {

	@Autowired
	private ShopQueryService shopQueryService;

	@Autowired
	private ShopAddService shopAddService;

	@Autowired
	private ShopStatusService shopStatusService;

	@Autowired
	private ShopNameService shopNameService;

	@Autowired
	private ShopProfileImageService shopProfileImageService;

	@Autowired
	private ShopAddressService shopAddressService;
	
	@Autowired
	private ShopDocumentService shopDocumentService;
	
	@Autowired
	private DataConverter dataConverter;

	/**
	 * Handles API request for getting details of shop
	 * 
	 * @param email String representing email of the shop which can uniquely
	 *              identify it
	 * @return JSON string representing details of the shop
	 * 
	 * @see ShopEntity
	 * 
	 * @author Ashish Sam T George
	 * 
	 */
	@GetMapping
	public String shopInfo() {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		ShopEntity shopEntity = shopQueryService.shopInfo(email);
		String shop = dataConverter.convertToJson(shopEntity);
		return shop;
	}

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
	@PostMapping
	public String addShop(@RequestBody ShopEntity shopEntity) {
		ShopEntity savedShopEntity = shopAddService.addShop(shopEntity);
		String shop = dataConverter.convertToJson(savedShopEntity);
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
	@DeleteMapping
	public String disableShop() throws InterruptedException, IOException {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		ShopEntity disabledShop = shopStatusService.disableShop(email);
		String shop = dataConverter.convertToJson(disabledShop);
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
	@PatchMapping
	public String enableShop() throws InterruptedException, IOException {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();
		
		ShopEntity enabledShop = shopStatusService.enableShop(email);
		String shop = dataConverter.convertToJson(enabledShop);
		return shop;
	}

	@PutMapping("/fullName")
	public String changeFullName(@RequestParam String fullName) throws InterruptedException, IOException {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();

		ShopEntity modifiedShop = shopNameService.changeFullName(email, fullName);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}

	@PutMapping("/profileImage")
	public String changeProfileImage(@RequestParam String imageUrl) {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();

		ShopEntity modifiedShop = shopProfileImageService.changeProfileImage(email, imageUrl);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}
	
	@PutMapping("/documentUrl")
	public String changeDocumentUrl(@RequestParam String documentUrl) {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();

		ShopEntity modifiedShop = shopDocumentService.changeDocument(email, documentUrl);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}

	@PutMapping("/address")
	public String changeAddress(@RequestBody ShopAddressModel shopAddressModel) {
		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
		String email = (String) authenticatedUser.getPrincipal();

		ShopEntity modifiedShop = shopAddressService.changeAddress(email, shopAddressModel);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}
	
//	@PostMapping("/product")
//	public String addProducts() {
//		Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
//		String email = (String) authenticatedUser.getPrincipal();
//
//	}
	
}
