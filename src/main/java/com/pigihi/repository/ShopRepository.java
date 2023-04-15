/**
 * 
 */
package com.pigihi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.pigihi.entity.ShopEntity;

/**
 * Repository class for shop
 * 
 * @author Ashish Sam T George
 *
 */
public interface ShopRepository extends MongoRepository<ShopEntity, String> {

	@Query("{email: ?0}")
	ShopEntity findByEmail(String email);

	@Query("{$or: [{'email': ?0}, {'mobile': ?1}]}")
	List<ShopEntity> findByEmailAndMobile(String email, String mobile);

}
