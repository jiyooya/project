package com.foke.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

	// 이용약관 페이지
	@GetMapping("/usepolicy")
	public String usepolicy() {
		return "usepolicy";
	}

	// 개인정보처리방침 페이지
	@GetMapping("/privacy")
	public String privacy() {
		return "privacy";
	}

	@GetMapping("/")
	public String header() {
		return "/fragments/header2";
	}

	// HomeController.java

	@GetMapping("/home")
	public String goHome(HttpServletRequest request) {
		return "test";
	}
}