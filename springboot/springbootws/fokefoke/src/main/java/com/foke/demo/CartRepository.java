package com.foke.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foke.demo.dto.CartDTO;

public interface CartRepository extends JpaRepository<CartDTO, Integer> {
	
	//List<CartDTO> findByMemberId(String memberId);
	//List<CartDTO> findByCartId(int cartId);
	//<합칠떄>
	CartDTO findByMemberIdAndFokeingredientIdAndStoreId(String memberId, int fokeingredientId, int storeId);
	
	CartDTO findByMemberIdAndProductName(String memberId, String productName);
}
