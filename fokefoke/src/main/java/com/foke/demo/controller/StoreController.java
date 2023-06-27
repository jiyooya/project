package com.foke.demo.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foke.demo.dto.StoreDTO;
import com.foke.demo.service.StoreService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/store")
public class StoreController {
	
	private final StoreService storeService;
	
	@GetMapping("/list")
	public String list(Model model,@RequestParam(value="page", defaultValue="0")int page,@Param("keyword") String keyword) {
		Page<StoreDTO> list = null;
		if(keyword == null) {
			list = this.storeService.getList(page);
		}else {
			list = this.storeService.search(keyword,page);
			model.addAttribute("keyword",keyword);
		}
		model.addAttribute("paging",list);
		
		return "store/list";
	}
	@RequestMapping(value = "/listto", method = RequestMethod.GET)
	@ResponseBody
	public Page<StoreDTO> getFilteredMap(HttpServletRequest request,@RequestParam(value="page", defaultValue="0")int page) {
		String keywords = request.getParameter("keyword");
		Page<StoreDTO> list =  storeService.search(keywords,page);
		return list;
	}
	
}
