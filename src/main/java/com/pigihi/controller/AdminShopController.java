package com.pigihi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.pigihi.entity.ShopEntity;
import com.pigihi.library.dataConverter.service.DataConverter;
import com.pigihi.model.ShopAddressModel;
import com.pigihi.service.admin.AdminShopAddressService;
import com.pigihi.service.admin.AdminShopApproveService;
import com.pigihi.service.admin.AdminShopNameService;
import com.pigihi.service.admin.AdminShopProfileImageService;
import com.pigihi.service.admin.AdminShopQueryService;
import com.pigihi.service.admin.AdminShopStatusService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user/shop/admin")
public class AdminShopController {
	
	@Autowired
	private AdminShopQueryService adminShopQueryService;
	
	@Autowired
	private AdminShopStatusService adminShopStatusService;
	
	@Autowired
	private AdminShopNameService adminShopNameService;
	
	@Autowired
	private AdminShopProfileImageService adminShopProfileImageService;
	
	@Autowired
	private AdminShopAddressService adminShopAddressService;
	
	@Autowired
	private AdminShopApproveService adminShopApproveService;
	
	@Autowired
	private DataConverter dataConverter;
	
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
	@GetMapping("/listShopsById")
	public String getShopList(@RequestParam List<String> shopIds) {
		List<ShopEntity> shopEntities = adminShopQueryService.findShopsById(shopIds);
		String shops = dataConverter.convertToJson(shopEntities);
		return shops;
	}
	
	@GetMapping("/all")
	public String getAllShops() {
		List<ShopEntity> shopEntities = adminShopQueryService.findAllShops();
		String shops = dataConverter.convertToJson(shopEntities);
		return shops;
	}
	
	@GetMapping
	public String getShopInfo(@RequestParam String email) {
		ShopEntity shop = adminShopQueryService.findShop(email);
		String shopJson = dataConverter.convertToJson(shop);
		return shopJson;
	}
	
	@PatchMapping
	public String enableShopByAdmin(@RequestParam String email) throws IOException, InterruptedException {
		ShopEntity shop = adminShopStatusService.enableShopByAdmin(email);
		String shopJson = dataConverter.convertToJson(shop);
		return shopJson;
	}
	
	@DeleteMapping
	public String disableShopByAdmin(@RequestParam String email) throws IOException, InterruptedException {
		ShopEntity shop = adminShopStatusService.disableShopByAdmin(email);
		String shopJson = dataConverter.convertToJson(shop);
		return shopJson;
	}
	
	@PutMapping("/shopName")
	public String changeShopName(@RequestParam String email,
									@RequestParam String shopName) throws IOException, InterruptedException {
		ShopEntity modifiedShop = adminShopNameService.changeShopName(email, shopName);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}
	
	@PutMapping("/profileImage")
	public String changeShopProfileImage(@RequestParam String email,
											@RequestParam String imageUrl) throws IOException, InterruptedException {
		ShopEntity modifiedShop = adminShopProfileImageService.changeProfileImage(email, imageUrl);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}
	
	@PutMapping("/address")
	public String changeShopAddress(@RequestParam String email,
										@RequestBody ShopAddressModel shopAddressModel) throws IOException, InterruptedException {
		ShopEntity modifiedShop = adminShopAddressService.changeAddress(email, shopAddressModel);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}
	
	@PatchMapping("/approve")
	public String approveShop(@RequestParam String email) {
		ShopEntity modifiedShop = adminShopApproveService.approveShop(email);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}
	
	@PatchMapping("/disapprove")
	public String disapproveShop(@RequestParam String email) {
		ShopEntity modifiedShop = adminShopApproveService.disapproveShop(email);
		String shopJson = dataConverter.convertToJson(modifiedShop);
		return shopJson;
	}

	//TODO How to delete a user rather than changing the enable status

}
