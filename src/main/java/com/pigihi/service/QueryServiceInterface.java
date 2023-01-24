/**
 * 
 */
package com.pigihi.service;

import java.util.List;

import com.pigihi.entity.ShopEntity;

/**
 * @author Ashish Sam T George
 *
 */
public interface QueryServiceInterface {

	ShopEntity shopInfo(String email);

	List<ShopEntity> findShopsById(List<String> shopIds);

}
