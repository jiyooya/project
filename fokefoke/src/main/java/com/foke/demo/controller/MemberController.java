package com.foke.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foke.demo.repository.MemberRepository;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private final MemberRepository memberRepository;
	
	public MemberController() {
		System.out.println("member@Controller 스프링 자동 생성");
	}
	
	// 로그인 페이지 이동
	@GetMapping(value = "/login")
	public String login() {
		return "test";
	}

}
