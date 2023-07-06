package com.foke.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foke.demo.dto.CartDTO;
import com.foke.demo.dto.DetailDTO;
import com.foke.demo.dto.ProductDTO;
//import com.fokefoke.dto.CartDTO;
//import com.fokefoke.dto.DetailDTO;
//import com.fokefoke.dto.ProductDTO;
//import com.fokefoke.service.CartService;
//import com.fokefoke.service.DetailService;
import com.foke.demo.service.CartService;
import com.foke.demo.service.DetailService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/cart")
@RequiredArgsConstructor
@Controller
public class CartController {
	
	private final HttpSession session;
	private final CartService cartService;
	private final DetailService detailService;
	
	//리스트(합칠때)
	@PostMapping("/view")
    public String listCart(Model model, @ModelAttribute CartDTO cartDTO, HttpSession session) {
//      String memberId = (String) session.getAttribute("memberId");
//      List<CartDTO> cartList = this.cartService.getCartList(memberId);
		
		@SuppressWarnings("unchecked")
        //List<CartDTO> cartList = this.cartService.getCartList();
		List<CartDTO> cartInfo = (List<CartDTO>)session.getAttribute("cartList2");
		model.addAttribute("cartInfo", cartInfo);
        System.out.println(cartInfo);
        
        return "cart/cart";
    }
	
	@ResponseBody
	@PostMapping("/add")
	public int addCartPOST(Model model, @RequestParam Map<String, String> map, 
			DetailDTO dto, ProductDTO productDTO,
			@RequestParam(required = false, defaultValue="foke") List<String> toppingchk, 
			@RequestParam(required = false, defaultValue="foke") List<String> sourcechk, 
			@RequestParam(required = false, defaultValue="foke") List<String> extrachk){
		
		if (toppingchk != null) {
			for(int i=0;i<toppingchk.size();i++) {
				if(i==0) {
					dto.setAtopping(toppingchk.get(i));
				}else if(i==1) {
					dto.setBtopping(toppingchk.get(i));
				}else if(i==2) {
					dto.setCtopping(toppingchk.get(i));
				}else if(i==3) {
					dto.setDtopping(toppingchk.get(i));
				}
			}
		}
		if (sourcechk != null) {
			for(int i=0;i<sourcechk.size();i++) {
				if(i==0) {
					dto.setAsource(sourcechk.get(i));
				}else if(i==1) {
					dto.setBsource(sourcechk.get(i));
				}
			}
		}
		if(extrachk!=null) {
			for(int i=0;i<extrachk.size();i++) {
				if(i==0) {
					dto.setAextratopping(extrachk.get(i));
				}else if(i==1) {
					dto.setBextratopping(extrachk.get(i));
				}
			}
		}

		List<DetailDTO> detailList =  this.detailService.getList();
		
		
		//store
	    String storeName = (String) session.getAttribute("storeName");
	    String storeAddress = (String) session.getAttribute("storeAddress");
		
		CartDTO cart = CartDTO.builder()
				.fokeingredientId(dto.getFokeingredientId())
				.memberId(dto.getMemberId())
				.productName(dto.getProductName())
				.base(dto.getBase())
				.atopping(dto.getAtopping())
				.btopping(dto.getBtopping())
				.ctopping(dto.getCtopping())
				.dtopping(dto.getDtopping())
				.asource(dto.getAsource())
				.bsource(dto.getBsource())
				.aextratopping(dto.getAextratopping())
				.bextratopping(dto.getBextratopping())
				.extramain(dto.getExtramain())
				.setbeverage(dto.getSetbeverage())
				.setside(dto.getSetside())
				.total(dto.getTotal())
				.price(dto.getPrice())
				.onePrice(dto.getPrice() / dto.getTotal())
				.cartImage(dto.getProductImage())
				.cartCount(dto.getTotal())
	            .storeName(storeName)
	            .storeAddress(storeAddress)
			    .build();
		
		cart.initTotal(); // initTotal() 호출
		cartService.insertCart(cart);
		
//		//CartDTO cart = new CartDTO();
//	    cart.setCartCount(dto.getTotal());
//	    cart.setCartImage(dto.getProductImage());
//	    //cart.setMemberId((String) session.getAttribute("memberId"));
//	    //cart.setStoreId(Integer.parseInt((String) session.getAttribute("storeId")));
//	    cart.setFokeingredientId(dto.getFokeingredientId());
//	    cart.setProductName(dto.getProductName());
//	    cart.setBase(dto.getBase());
//	    cart.setAtopping(dto.getAtopping());
//	    cart.setAsource(dto.getAsource());
//	    //cart.getOnePrice(dto.getOnePrice());
	    
	    List<CartDTO> cartList2 = new ArrayList<>();
	    cartList2.add(cart);
	    session.setAttribute("cartList2", cartList2);
		
		System.out.println("map정보 : " + map);
		System.out.println("topping정보 : " + toppingchk);
		System.out.println("detail 이미지 : " + dto.getProductImage());
		System.out.println("detail a소스 : " + dto.getAsource());
		System.out.println("detail 베이스 : " + dto.getBase());
		System.out.println("detail  : " + dto.getBase());
		System.out.println("detail : " +  dto.getFokeingredientId());
		System.out.println("detail oneprice: " +  dto.getPrice() / dto.getTotal());
		
		System.out.println("detailList : " + detailList);
		
		System.out.println("=====================================================");
		
		System.out.println("cartDTO 정보 : " + cart.getProductName());
		System.out.println("cartDTO 정보 : " + cart.getAtopping());
		System.out.println("cartDTO a소스 : " + cart.getAsource());
		System.out.println("cartDTO Id : " +  cart.getFokeingredientId());
		System.out.println("cartDTO 베이스 : " + cart.getBase());
		System.out.println("cartDTO Id : " +  cart.getPrice());
		System.out.println("cartDTO 이미지 : " + cart.getCartImage());
		System.out.println("cartDTO total : " + cart.getTotal());
		System.out.println("cartDTO onePrice : " + cart.getOnePrice());
		System.out.println("cartDTO storeName : " + cart.getStoreName());
		System.out.println("cartDTO storeAddress : " + cart.getStoreAddress());
		System.out.println("cartDTO 얻는포인트 : " + cart.getPoint());
		System.out.println("cartDTO 전체포인트 : " + cart.getTotalPoint());
	
		
		System.out.println("cartList2 : " + cartList2);
		
		int result = this.cartService.addCart(cart);
		
		return result;
	}
	
	
//	@PostMapping("/add")
//    @ResponseBody
//    public int addCartPOST(HttpSession session, @RequestBody DetailDTO dto,
//                           @RequestParam(required = false) List<String> toppingchk,
//                           @RequestParam(required = false) List<String> sourcechk,
//                           @RequestParam(required = false) List<String> extrachk) {
//        if (toppingchk != null) {
//            for (int i = 0; i < toppingchk.size(); i++) {
//                if (i == 0) {
//                    dto.setAtopping(toppingchk.get(i));
//                } else if (i == 1) {
//                    dto.setBtopping(toppingchk.get(i));
//                } else if (i == 2) {
//                    dto.setCtopping(toppingchk.get(i));
//                } else if (i == 3) {
//                    dto.setDtopping(toppingchk.get(i));
//                }
//            }
//        }
//        if (sourcechk != null) {
//            for (int i = 0; i < sourcechk.size(); i++) {
//                if (i == 0) {
//                    dto.setAsource(sourcechk.get(i));
//                } else if (i == 1) {
//                    dto.setBsource(sourcechk.get(i));
//                }
//            }
//        }
//        if (extrachk != null) {
//            for (int i = 0; i < extrachk.size(); i++) {
//                if (i == 0) {
//                    dto.setAextratopping(extrachk.get(i));
//                } else if (i == 1) {
//                    dto.setBextratopping(extrachk.get(i));
//                }
//            }
//        }
//
//        //System.out.println("post---------------" + session.getAttribute("memberId"));
//        //System.out.println("post---------------" + session.getAttribute("storeId"));        
//        //service.insertDetail(dto);
//		//List<DetailDTO> detailList =service.getFokeingredient(dto);
//
//        List<DetailDTO> detailList =  this.detailService.getList();
//        
//        List<CartDTO> cartList = new ArrayList<>();
//        for (DetailDTO detail : detailList) {
//            CartDTO cart = new CartDTO();
//            cart.setCartCount(detail.getTotal());
//            cart.setCartImage(detail.getProductImage());
//            cart.setMemberId((String) session.getAttribute("memberId"));
//            cart.setStoreId(Integer.parseInt((String) session.getAttribute("storeId")));
//            cart.setFokeingredientId(detail.getFokeingredientId());
//            cartList.add(cart);
//        }
//        
//      //카트등록
//  		int result = this.cartService.addCart(null);
//  		System.out.println(result);
//  		
//  		return result;
//     }
	
	
	
	//@GetMapping("/{memberId}")
	//public String cartPageGET(@PathVariable("memberId") String memberId, Model model) {
	
//	@GetMapping("/view")
//	public String cartPageGET(Model model) {
//	  //List<CartDTO> cartList = cartService.getCartList(memberId);
//	    List<CartDTO> cartList = this.cartService.getCartList();
//	    model.addAttribute("cartInfo", cartList);
//	    // Model 객체의 addAttribute를 활용해 장바구니 데이터 전송
//	    return "cart/cart";
//    }

	@ResponseBody
	@PostMapping("/cart/update")
	public String updateCartPOST(@RequestBody CartDTO cart) {
	    int result = cartService.modifyCount(cart); // Service 메서드 호출
	    return String.valueOf(result);
	}

	@PostMapping("/cart/delete")
	public String deleteCartPOST(@RequestBody CartDTO cart, HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    cartService.deleteCart(cart.getCartId());
	    cart.setMemberId((String) session.getAttribute("memberId"));
	    return "redirect:/cart/" + cart.getMemberId();
	}


}


