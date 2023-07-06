package com.foke.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.foke.demo.dto.CartDTO;
import com.foke.demo.dto.CartListDTO;
import com.foke.demo.dto.DetailDTO;
import com.foke.demo.dto.MemberDTO;
import com.foke.demo.dto.PaymentDTO;
import com.foke.demo.dto.ProductDTO;
import com.foke.demo.dto.StoreDTO;
import com.foke.demo.service.CartService;
import com.foke.demo.service.DetailService;
import com.foke.demo.service.Paymentservice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private final Paymentservice paymentservice;
//	private final Memberservice memberservice;
	private final DetailService detailService;
	private final CartService cartService;
	
	//카트 - 결제 페이지
	@RequestMapping(value = "list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(HttpServletRequest request, Model model, PaymentDTO pdto, ProductDTO pro, MemberDTO mdto, @RequestParam(required=false) List<String> cartId) {
		
		HttpSession session = request.getSession();
		StoreDTO sdto = new StoreDTO();
		String memberId = (String)session.getAttribute("memberId");
//		mdto.setMemberId(memberId);
		mdto.setMemberId("test1@1");
		MemberDTO member = this.paymentservice.getMember(mdto.getMemberId());
		PaymentDTO pay = new PaymentDTO();
		pay.setMemberId((String)session.getAttribute("memberId"));
		
		//장바구니 정보 리스트
		List<CartDTO> cartList = cartService.getCartList();
		List<CartDTO> cartLists = new ArrayList<CartDTO>();
		for(int j=0; j<cartList.size();j++) {
			for(int i=0; i<cartId.size();i++) {
				if(cartList.get(j).getCartId()==Integer.parseInt(cartId.get(i))) {
					System.out.println("??????" + cartList.get(j));
					cartLists.add(cartList.get(j));
					sdto.setStoreAddress(cartList.get(j).getStoreAddress());
		            sdto.setStoreName(cartList.get(j).getStoreName());
				}
			}
		}
			System.out.println(">>>>>>>>>Lists>>>>>>>>>>>>>>>>>>>>>>"+ cartLists);
			System.out.println("카트아이디이~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+cartId);
	//테스트
	mdto.setPoint(1000);
	mdto.setPhone("010");
	
	model.addAttribute("store", sdto);
	model.addAttribute("member", mdto);
	model.addAttribute("cart", cartLists);
	//포인트 적립
//	HttpSession mession = request.getSession();
//	mession.setAttribute("pmember", member);
		return "payment/payment_list";
	}
	
	//카트 - 뷰 페이지
	@RequestMapping(value = "order", method={RequestMethod.GET, RequestMethod.POST})
	public String order(HttpServletRequest request, Model model, PaymentDTO pdto, ProductDTO pro, MemberDTO mdto, @RequestParam(required=false) List<String> cartId) {
	
		HttpSession session = request.getSession();
		StoreDTO sdto = new StoreDTO();
		sdto.setStoreName((String)session.getAttribute("storeName"));
		sdto.setStoreAddress((String)session.getAttribute("StoreAddress"));
		String memberId = (String)session.getAttribute("memberId");
//		mdto.setMemberId(memberId);
		mdto.setMemberId("test1@1");
		
		
		//장바구니 정보 리스트
		List<CartDTO> cartList = cartService.getCartList();
		List<CartDTO> cartLists = new ArrayList<CartDTO>();
			for(int j=0; j<cartList.size();j++) {
				for(int i=0; i<cartId.size();i++) {
					if(cartList.get(j).getCartId()==Integer.parseInt(cartId.get(i))) {
						System.out.println("??????" + cartList.get(j));
						cartLists.add(cartList.get(j));
						sdto.setStoreAddress(cartList.get(j).getStoreAddress());
				           sdto.setStoreName(cartList.get(j).getStoreName());
					}
				}
			}
				System.out.println(">>>>>>>>>Listssssss>>>>>>>>>>>>>>>>>>>>>>"+ cartLists);
				System.out.println("카트아이디이~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+cartId);
				
				String orderNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				Random random = new Random();
				StringBuilder sb = new StringBuilder(15);
				for(int i=0; i<15; i++){
					int randomNum = random.nextInt(orderNum.length());
				    sb.append(orderNum.charAt(randomNum));
				}
				String randomString = sb.toString();		
				
		//포인트 적립
//		HttpSession mession = request.getSession();
		
		//테스트
		mdto.setPhone("010");
		mdto.setPoint(1000);
		
		
		model.addAttribute("store", sdto);
		model.addAttribute("member", mdto);
		model.addAttribute("cart", cartLists);
		model.addAttribute("randomString", randomString);
		
		return "payment/payment_order";
		
	}
	
	//뷰 - 결제페이지
	@RequestMapping(value = "list1", method={RequestMethod.GET, RequestMethod.POST})
	public String list1(HttpServletRequest request, Model model, PaymentDTO pdto, ProductDTO pro, MemberDTO mdto, DetailDTO ddto, @RequestParam(required = false) List<String> toppingchk, 
			@RequestParam(required = false) List<String> sourcechk, @RequestParam(required = false) List<String> extrachk) {


		if (toppingchk != null) {
			for(int i=0;i<toppingchk.size();i++) {
				if(i==0) {
					ddto.setAtopping(toppingchk.get(i));
				}else if(i==1) {
					ddto.setBtopping(toppingchk.get(i));
				}else if(i==2) {
					ddto.setCtopping(toppingchk.get(i));
				}else if(i==3) {
					ddto.setDtopping(toppingchk.get(i));
				}
			}
		}
		if (sourcechk != null) {
			for(int i=0;i<sourcechk.size();i++) {
				if(i==0) {
					ddto.setAsource(sourcechk.get(i));
				}else if(i==1) {
					ddto.setBsource(sourcechk.get(i));
				}
			}
		}
		if(extrachk!=null) {
			for(int i=0;i<extrachk.size();i++) {
				if(i==0) {
					ddto.setAextratopping(extrachk.get(i));
				}else if(i==1) {
					ddto.setBextratopping(extrachk.get(i));
				}
			}
		}
		
		HttpSession session = request.getSession();
		StoreDTO sdto = new StoreDTO();
		sdto.setStoreName((String)session.getAttribute("storeName"));
		sdto.setStoreAddress((String)session.getAttribute("storeAddress"));
		String memberId = (String)session.getAttribute("memberId");
		mdto.setMemberId(memberId);
		
		MemberDTO member = this.paymentservice.getMember(mdto.getMemberId());
		PaymentDTO pay = new PaymentDTO();
		pay.setMemberId((String)session.getAttribute("memberId"));
		
		//테스트
		mdto.setPoint(1000);
		mdto.setPhone("010");
		//detail 데이터 세션을 담음
		session.setAttribute("detail", ddto);
		
		model.addAttribute("store", sdto);
		model.addAttribute("member", mdto);
		model.addAttribute("detail", ddto);
		
		//포인트 적립
//		HttpSession mession = request.getSession();
//		mession.setAttribute("pmember", member);
		
		return "payment/payment_list1";
	}
	
	//디테일 - 오더 페이지
	@RequestMapping(value = "order1", method={RequestMethod.GET, RequestMethod.POST})
	public String order1(HttpServletRequest request, Model model, PaymentDTO pdto, ProductDTO pro, MemberDTO mdto, DetailDTO ddto) {
	
		HttpSession session = request.getSession();
		DetailDTO sessionddto = (DetailDTO)session.getAttribute("detail");
		StoreDTO sdto = new StoreDTO();
		sdto.setStoreName((String)session.getAttribute("storeName"));
		sdto.setStoreAddress((String)session.getAttribute("storeAddress"));
		String memberId = (String)session.getAttribute("memberId");
		mdto.setMemberId(memberId);
		
		HttpSession mession = request.getSession();
		MemberDTO pmember = (MemberDTO)mession.getAttribute("pmember");
		pmember = this.paymentservice.getMember(memberId);
		
		//테스트
		mdto.setPoint(1000);
		mdto.setPhone("010");
		
		model.addAttribute("store", sdto);
		model.addAttribute("member", mdto);
		model.addAttribute("detail", sessionddto);
		model.addAttribute("pmember", pmember);
		
		
		return "payment/payment_order1";
	}
	


	//카카오큐알 테스트
	@PostMapping("/payment/test")
	public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		return "payment/payment_test";
	}
}
