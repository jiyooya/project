package com.foke.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
            throw new DataNotFoundException("회원을 찾을 수 없습니다.");
        }
    }
	
	// 회원 가입
	public void memberJoin(MemberDTO member) {
		String memberPw = member.getMemberPw();
		member.setMemberPw(pwEncoder.encode(memberPw));
	    this.memberRepository.save(member);
    }
	
	// 회원 아이디 중복 체크
	public String idCheck(String memberId) {  
		int idChk = this.memberRepository.countByMemberId(memberId);
        if (idChk != 0) {
            return "fail";
        } else {
            return "success";
        }
    }

	// 아이디 찾기
	public String findId(String memberName, String phone) {
		Optional<MemberDTO> member = this.memberRepository.findByMemberNameAndPhone(memberName, phone);
		if(member.isPresent()) {
			Optional<String> email = member.map(MemberDTO::getMemberId);
			String emailM = email.orElse("");
			emailM = emailM.replaceAll("(?<=.{3}).(?=.*@)", "*");
			return emailM;
		} else {
			return "fail"; // 아이디 x
		}
	}
	
	// 비밀번호 형식에 맞는 랜덤 문자 생성
	public String createPw() {
        String newPw = ""; // 임시 비번
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+~`{}[]:;<>?,./";
        StringBuilder text = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 15; i++) {
            text.append(possible.charAt(random.nextInt(possible.length())));
        }
        
        newPw = text.toString(); // 임시 비밀번호
        System.out.println(newPw);
        return newPw;
	}

	// 비밀번호 재설정
	public void setPw(MemberDTO member) {
		String newPw = ""; 
		String encodeNewPw = ""; // 암호화된 임시비번
		Optional<MemberDTO> optionalMember = this.memberRepository.findBymemberId(member.getMemberId());
		if (optionalMember.isPresent()) {
		    MemberDTO member2 = optionalMember.get();
		    newPw = createPw(); // 임시 비번
			System.out.println(newPw);
			encodeNewPw = pwEncoder.encode(newPw); // 비밀번호 암호화
			System.out.println(encodeNewPw);
			member2.setMemberPw(encodeNewPw);
			System.out.println(member2.toString());
			this.memberRepository.save(member2);
		} else {
		    // Optional에 값이 없는 경우 처리 로직 작성
		}
		
		
	}
}
