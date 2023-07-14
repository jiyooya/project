package com.foke.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.foke.demo.dto.DetailDTO;
import com.foke.demo.dto.MemberDTO;
import com.foke.demo.dto.PaymentDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Paymentservice {

	private final PaymentRepository paymentRepository;

	public MemberDTO getMember(String memberId){
		return this.paymentRepository.findPaymentMemberByMemberId(memberId);
	}
	//해당 db에 올리는 역할(save)
	public PaymentDTO savePayment(PaymentDTO paymentDTO) {
		return paymentRepository.save(paymentDTO);
		
	}
	
    //결제 상품차트
    public List<Object[]> getMostAddedProducts() {
        return paymentRepository.findMostAddedProducts();
    }
    
    //결제 지역차트
    public List<Object[]> getMostAddedStore() {
    	return paymentRepository.findMostAddedStore();
    }
    
    //결제 매출차트
    public List<Object[]> getRevenue() {
    	return paymentRepository.findRevenue();
    }
}
