package com.foke.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foke.demo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller	
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;

	// 비밀번호 확인 페이지 이동
	@GetMapping(value = "/mypage/info")
	public String checkPasswordGET() {
		return "member/mypage_info";
	}

	// (삭제필)회원정보 수정 페이지 이동
	@GetMapping(value = "/mypage/edit")
	public String editMemberGET(HttpServletRequest request, Model model) throws Exception {
		return "member/edit";
	}

	// (삭제필)마케팅 수신 설정 페이지 이동
	@GetMapping(value = "/mypage/push_setting")
	public String pushMemberGET() {
		return "member/push_setting";
	}

	// 비밀번호 재설정 페이지 이동
	@GetMapping(value = "/mypage/edit_password")
	public String editPwMemberGET() {
		return "member/edit_password";
	}

	// 회원 탈퇴 페이지 이동
	@GetMapping(value = "/mypage/withdrawals")
	public String withdrawalsMemberGET() {
		return "member/withdrawals";
	}
}