package com.foke.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foke.demo.dto.MemberDTO;
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
		// 시험 코드
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails)principal;

		String username = userDetails.getUsername();
		String password = userDetails.getPassword();
		
		System.out.println(username);
		System.out.println(password);
		// 여기까지
		return "member/mypage_info";
	}

	// 회원 비밀번호 확인
	@PostMapping(value = "/memberPwChk")
	@ResponseBody
	public String memberPwChkPOST(MemberDTO member) throws Exception {
		System.out.println(member.toString());
		String result = memberService.pwChk(member);
		System.out.println(result);
		return result;
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