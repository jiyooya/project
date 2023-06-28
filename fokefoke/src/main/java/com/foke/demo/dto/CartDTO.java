package com.foke.demo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "cart")
@Entity
public class CartDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	
	@Column(length = 100)
	private String memberId;
	
	@Column
	private int fokeingredientId;
	
	@Column
	private int storeId;
	
	@Column
	private int cartCount;
	
	@Column(length = 100)
	private String productName;
	
	@Column(length = 100, nullable = false)
	private String base;
	
	@Column(length = 100, nullable = false)
	private String atopping;
	
	@Column(length = 100, nullable = false)
	private String btopping;
	
	@Column(length = 100, nullable = false)
	private String ctopping;
	
	@Column(length = 100, nullable = false)
	private String dtopping;
	
	@Column(length = 100, nullable = false)
	private String asource;
	
	@Column(length = 100, nullable = false)
	private String bsource;
	
	@Column(length = 100, nullable = false)
	private String aextratopping;
	
	@Column(length = 100, nullable = false)
	private String bextratopping;
	
	@Column(length = 100, nullable = false)
	private String extramain;
	
	@Column(length = 100, nullable = false)
	private String setbeverage;
	
	@Column(length = 100, nullable = false)
	private String setside;
	
	@Column
	private int total;
	
	@Column
	private int price;
	
	@Column(nullable = false)
	private int onePrice;
	
	//member테이블
	@Column(nullable = false)
	private int point;
	
	//store 테이블
	@Column(length = 100, nullable = false)
	private String storeName;
	
	@Column(length = 100, nullable = false)
	private String storeAddress;
	
	//추가(가격)
	@Column
	private int totalPrice; //(+배송비)

	//추가(포인트)
	@Column
	private int totalPoint;
	
	//추가(상품이미지)
	@Column(length = 100, nullable = false)
	private String cartImage;
	
	public void initTotal() {
		//this.cartCount = this.total;
		this.totalPrice = this.onePrice * this.cartCount;
		this.point = (int)(Math.floor(this.onePrice*0.05));
		this.totalPoint = this.point * this.cartCount;
	}
}
