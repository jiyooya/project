package com.foke.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foke.demo.dto.MemberDTO;
import com.foke.demo.service.MailService;
import com.foke.demo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller	
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	MailService mailService;

	// 로그인 페이지 이동
	@GetMapping(value = "/login")
	public String login() {
		return "member/login";
	}

	// 메인 페이지 로그아웃
	@GetMapping(value = "/logout")
	public String logoutMainGET(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();

		return "redirect:/";
	}

	// 비동기방식 로그아웃
	@PostMapping(value = "logout.do")
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.invalidate();
	}

	// 회원가입 페이지 이동
	@GetMapping(value = "/join")
	public String joinGET() {
		return "member/join";
	}

	// 회원가입 서비스 실행
	@PostMapping("/join")
	public String joinPOST(MemberDTO member) {
		memberService.memberJoin(member);
		return "member/join_success";
	}

	// 아이디 중복 검사
	@PostMapping(value = "/memberIdChk")
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception {
		String result = memberService.idCheck(memberId);
		return result;
	}

	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheckGET(String memberId) throws Exception {
		String code = mailService.sendSimpleMessage(memberId);
		System.out.println("인증코드 : " + code);
		return code;
	}

	// 아이디 찾기 페이지 이동
	@GetMapping(value = "/find_email")
	public String findEmailGET() {
		return "member/find_email";
	}

	// 아이디 찾기 성공 페이지 이동
	@PostMapping(value = "/findId/success")
	public String findIdPOST(String memberId, Model model) throws Exception {
		model.addAttribute("memberId", memberId);
		System.out.println(model.getAttribute("memberId"));

		return "/member/find_email_success";
	}

	// (삭제필)아이디 찾기 성공 페이지 이동
	@GetMapping(value = "/findId/success")
	public String findIdGET() {
		return "member/find_email_success";
	}

	// 비밀번호 찾기 페이지 이동
	@GetMapping(value = "/find_password")
	public String findPasswordGET() {
		return "member/find_password";
	}

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
