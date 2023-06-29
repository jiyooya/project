package com.foke.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foke.demo.dto.MemberDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, String> {
	int countByMemberId(String memberId);
	Optional<MemberDTO> findBymemberId(String memberId);
	MemberDTO findByMemberIdAndMemberPw(String memberId, String MemberPw);
	List<MemberDTO> findByMemberIdLike(String memberId);
}