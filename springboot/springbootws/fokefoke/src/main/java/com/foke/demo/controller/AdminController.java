package com.foke.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

	// 관리자 메인 페이지 이동
	@GetMapping(value = "/")
	public String login() {
		return "admin/adminroot";
	}

}
