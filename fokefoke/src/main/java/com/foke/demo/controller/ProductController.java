package com.foke.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foke.demo.dto.ProductDTO;
import com.foke.demo.dto.StockDTO;
import com.foke.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping("/list")
	public String list(Model model, @Param("storeId") int storeId) {
		List<ProductDTO> list = productService.getList();
		List<ProductDTO> salad = productService.typeSalad();
		List<ProductDTO> side = productService.typeSide();
		List<ProductDTO> drink = productService.typeDrink();
		List<StockDTO> stockList = productService.quantity(storeId);
		model.addAttribute("list", list);
		model.addAttribute("salad", salad);
		model.addAttribute("side", side);
		model.addAttribute("drink", drink);
		model.addAttribute("stock", stockList);

		return "product/list";

	}
}
