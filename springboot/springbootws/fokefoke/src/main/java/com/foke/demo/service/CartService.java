package com.foke.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.foke.demo.CartRepository;
import com.foke.demo.dto.CartDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    
    
    /*(boot)장바구니 추가 */
//	public int addCart(CartDTO cart) {
//        CartDTO checkCart = (CartDTO)this.cartRepository.findAll();
//		
//        if (checkCart != null) { // 등록하려는 데이터가 DB에 존재하는지 확인하고 존재할 경우 2를 반환
//            return 2;
//        }
//        try {
//            cartRepository.save(cart);
//            return 1; // 장바구니 등록 시 1을 반환
//        } catch (Exception e) {
//            return 0; // 오류 발생 시 0을 반환
//        }
//	}
	
    public int addCart(CartDTO cart) {
        CartDTO checkCart = cartRepository.findByMemberIdAndProductName(
                cart.getMemberId(), cart.getProductName());
 
        if (checkCart != null) {
            return 2;
        }

        try {
            cartRepository.save(cart);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    
    /*(boot)장바구니 정보 리스트 */
    public List<CartDTO> getCartList() {
        List<CartDTO> cartList = this.cartRepository.findAll();
        //List<CartDTO> cart = cartMapper.getCart(memberId);
        
        for(CartDTO cart : cartList) {
            cart.initTotal(); // CartDTO의 cartPrice, totalPrice, point, totalPoint 값을 초기화
        }
        return cartList;  // 장바구니 페이지의 사용자 장바구니 정보를 반환        
    }
	
	
//    /* 장바구니 추가 */
//    public int addCart(CartDTO cart) {
//        CartDTO checkCart = this.cartRepository.findByMemberIdAndFokeingredientId(cart.getMemberId(), cart.getFokeingredientId());
//		
//        if (checkCart != null) { // 등록하려는 데이터가 DB에 존재하는지 확인하고 존재할 경우 2를 반환
//            return 2;
//        }
//        try {
//            cartRepository.save(cart);
//            return 1; // 장바구니 등록 시 1을 반환
//        } catch (Exception e) {
//            return 0; // 오류 발생 시 0을 반환
//        }
//    }

//    /* 장바구니 정보 리스트 */
//    public List<CartDTO> getCartList(String memberId) {
//        List<CartDTO> cartList = this.cartRepository.findByMemberId(memberId);
//
//        for (CartDTO cart : cartList) {
//            cart.initTotal(); // CartDTO의 cartPrice, totalPrice, point, totalPoint 값을 초기화
//        }
//        return cartList;  // 장바구니 페이지의 사용자 장바구니 정보를 반환
//    }

    /* 장바구니 수량 수정 */
    public int modifyCount(CartDTO cart) {
        try {
            cartRepository.save(cart);
            return 1; // 장바구니 수량 수정 성공 시 1을 반환
        } catch (Exception e) {
            return 0; // 오류 발생 시 0을 반환
        }
    }

    /* 장바구니 삭제 */
    public int deleteCart(int cartId) {
        try {
            cartRepository.deleteById(cartId);
            return 1; // 장바구니 삭제 성공 시 1을 반환
        } catch (Exception e) {
            return 0; // 오류 발생 시 0을 반환
        }
    }
    
    public void insertCart(CartDTO cart) {
    	this.cartRepository.save(cart);
    	
    }
}
