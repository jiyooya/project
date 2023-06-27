package com.foke.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.foke.demo.DataNotFoundException;
import com.foke.demo.dto.MemberDTO;
import com.foke.demo.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder pwEncoder;
	
	// 회원 리스트 읽기
	public List<MemberDTO> getList() {
        return this.memberRepository.findAll();
    }
	
	// 회원 한명 읽기
	public MemberDTO getMember(String memberId) {  
        Optional<MemberDTO> member = this.memberRepository.findById(memberId);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
	
	// 회원 추가
	public MemberDTO memberJoin(MemberDTO member) {
		String memberPw = member.getMemberPw();
		member.setMemberPw(pwEncoder.encode(memberPw));
	    this.memberRepository.save(member);
	    return member;
    }
	
	// 회원 아이디 중복 체크
	public String idCheck(String memberId) {  
		System.out.println(memberId);
		int idChk = this.memberRepository.countByMemberId(memberId);
        if (idChk != 0) {
        	System.out.println("f");
            return "fail";
        } else {
        	System.out.println("s");
            return "success";
        }
    }

}
