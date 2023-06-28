package com.foke.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foke.demo.dto.NoticeDTO;
import com.foke.demo.dto.ProductDTO;
import com.foke.demo.service.NoticeService;
import com.foke.demo.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {
	
	private final ProductService productService;
	private final NoticeService noticeService;
	
	@GetMapping("/menuList")
    @ResponseBody
    public Map<String, Object> menuList() {
        List<ProductDTO> product = productService.getList();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("product", product);
        return resultMap;
    }
	@GetMapping("/noticeList")
    @ResponseBody
    public Map<String, Object> noticeList() {
        List<NoticeDTO> notice = noticeService.getList();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("notice", notice);
        return resultMap;
    }
	// 약관동의 페이지 이동
    @GetMapping(value = "/usepolicy")
    public void usepolicyGET() {
    }
    
    // 약관동의 페이지 이동
    @GetMapping(value = "/privacy")
    public void privacyGET() {
    }
}
