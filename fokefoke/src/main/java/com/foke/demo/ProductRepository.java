package com.foke.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foke.demo.dto.ProductDTO;

public interface ProductRepository extends JpaRepository<ProductDTO, Integer>{
	List<ProductDTO> findByProductType(String productType);
	ProductDTO findByProductName(String productName);
	List<ProductDTO> findAllByOrderByProductIdDesc();
}
