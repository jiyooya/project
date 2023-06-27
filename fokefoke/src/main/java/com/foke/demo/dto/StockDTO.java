package com.foke.demo.dto;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "stock")
@Entity
public class StockDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quantityId;
	
	@Column
	private int storeId;
	
	@Column
	private int productId;
	
	@Column
	private int quantity;
}
