/**
 * 
 */
package com.pigihi.service;

import java.util.List;

import com.pigihi.entity.ShopEntity;

/**
 * Interface for shop query service <br>
 * Not for methods that modify any values
 * 
 * @author Ashish Sam T George
 *
 */
public interface QueryServiceInterface {

	ShopEntity shopInfo(String email);

	List<ShopEntity> findShopsById(List<String> shopIds);

}
