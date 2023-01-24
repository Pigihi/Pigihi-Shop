/**
 * 
 */
package com.pigihi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pigihi.entity.ShopEntity;

/**
 * @author Ashish Sam T George
 *
 */
public interface ShopRepository extends MongoRepository<ShopEntity, String> {

	@Query("{email: ?0}")
	ShopEntity findByEmail(String email);

}
