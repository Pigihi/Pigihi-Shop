/**
 * 
 */
package com.pigihi.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Entity class for shop
 * 
 * @author Ashish Sam T George
 *
 */
@Document(collection = "shop_collection")
@Data
public class ShopEntity {
	
	@Id
	private String id;
	private String shopName;
	private String email;
	private String ownerFullName;
	private String role;
	private String mobile;
	private String imageUrl;
	private StatusEnum status = StatusEnum.ENABLED;
	private boolean verifyStatus = false;
	private String documentUrl;
	private String streetName;
	private String cityName;
	private String pincode;
	private List<String> productId;

}
