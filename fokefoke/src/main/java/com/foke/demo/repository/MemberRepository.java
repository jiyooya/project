package com.foke.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foke.demo.dto.MemberDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, String> {
	MemberDTO findByMemberId(String memberId);
	MemberDTO findByMoney(int money);
	MemberDTO findByMemberIdAndMemberPw(String memberId, String MemberPw);
	List<MemberDTO> findByMemberIdLike(String memberId);
}