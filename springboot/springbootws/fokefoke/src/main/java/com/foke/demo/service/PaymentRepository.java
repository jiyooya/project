package com.foke.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foke.demo.dto.MemberDTO;
import com.foke.demo.dto.PaymentDTO;

import jakarta.transaction.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDTO, Integer> {
	List<PaymentDTO> findByPaymentIdAndMemberIdAndFokeingredientIdAndCartId(int paymentId, String memberId, int fokeingredientId, int cartId);
	
	//멤버 정보 가져오기
	@Query(value = "SELECT m FROM MemberDTO m WHERE m.memberId = :memberId")
	MemberDTO findPaymentMemberByMemberId(@Param("memberId") String memberId);

}