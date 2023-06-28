package com.foke.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foke.demo.dto.ProductDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	
	public List<ProductDTO> getList(){
		return productRepository.findAll();
	}
	
}
