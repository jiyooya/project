package com.foke.demo.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.foke.demo.dto.MemberDTO;
import com.foke.demo.dto.NoticeDTO;
import com.foke.demo.service.AdminService;
import com.foke.demo.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final MemberService memberService;
	private final AdminService adminService;
	

	// 관리자 메인 페이지 이동
	@GetMapping("/main")
	public String adminmain() {
		return "admin/admin_main";
	}
	//관리자 목록페이지 이동
	@GetMapping("/adminlist")
	public String adminlist(HttpServletRequest request, Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<MemberDTO> paging = this.adminService.getList(page);
		model.addAttribute("paging", paging);
		
	    String currentUrl = request.getRequestURI();
	    model.addAttribute("currentUrl", currentUrl);
		return "admin/admin_adminlist.html";
	}
	
	// 관리자 회원관리 페이지 이동
	@GetMapping("/member")
	public String adminmember() {
		return "admin/admin_member";
	}
	
	@PostMapping("/enroll")
	public String noticeEnroll(@Valid MemberDTO memberDTO, BindingResult bindingResult,
	                           @RequestParam(value = "adminCk", required = false) Integer adminCk) throws IOException {
	  if (bindingResult.hasErrors()) {
	      return "admin/admin_enroll";
	  }

	  // 회원 등록 (관리자 및 일반 회원 모두 처리)
	  adminService.adminenroll(memberDTO, adminCk);

	  return "redirect:/admin/admin_list";
	}
	
	//회원목록 페이지
	@GetMapping("/list")
	public String list(HttpServletRequest request, Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<MemberDTO> paging = this.adminService.getList(page);
		model.addAttribute("paging", paging);
		
	    String currentUrl = request.getRequestURI();
	    model.addAttribute("currentUrl", currentUrl);
		return "admin/admin_list";
	}
	//회원 정보 페이지
		@GetMapping("/detail/{memberId}")
		public String detail(Model model, @PathVariable("memberId") String id) {
			MemberDTO memberdto = this.adminService.getmemberdto(id);
			model.addAttribute("memberdto", memberdto);
			return "admin/admin_detail";
		}
	
	//회원 삭제
    @PostMapping("/delete/{id}")
    public String memberDelete(@PathVariable("id") String id) {
    	adminService.memberdelete(id);
        return "redirect:/admin/list";
    }
    
    

}
