package com.pigihi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopAddressModel {
	
//	private String shopName;
	private String streetName;
	private String cityName;
	private String pincode;

}
