package com.foke.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foke.demo.DataNotFoundException;
import com.foke.demo.dto.MemberDTO;
import com.foke.demo.dto.NoticeDTO;
import com.foke.demo.repository.MemberRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class AdminService {
	
	private final MemberRepository memberRepository;
	
	
	// AdminService.java
	@Transactional
	public void adminenroll(MemberDTO memberDTO, Integer adminCk) {
	    MemberDTO Member = new MemberDTO();

	    // 기존 회원 정보 복사
	    BeanUtils.copyProperties(memberDTO, Member);
	  
	    Member.setAdminCk(adminCk);
	    memberRepository.save(Member);
	}
	
	// 회원 리스트 읽기
		public List<MemberDTO> getList() {
			return this.memberRepository.findAll();
		}
		
	//공지사항 상세조회
    public MemberDTO getmemberdto(String id) {  
        Optional<MemberDTO> member = this.memberRepository.findById(id);
        if (member.isPresent()) {
        	return member.get();
        } else {
            throw new DataNotFoundException("Member not found");
        }
    }
	
	//페이징
		public Page<MemberDTO> getList(int page) {
			List<Sort.Order> sorts = new ArrayList<>();
			sorts.add(Sort.Order.desc("birth"));
			Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
			return this.memberRepository.findAll(pageable);
		}
		
	//회원삭제
	public void memberdelete(String id) {
		memberRepository.deleteByMemberId(id);
	}
}
