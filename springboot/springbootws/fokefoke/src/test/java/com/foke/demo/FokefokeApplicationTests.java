package com.foke.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.foke.demo.dto.MemberDTO;
import com.foke.demo.repository.MemberRepository;


@SpringBootTest
class FokefokeApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testJpa() {
    	assertEquals(22, this.memberRepository.count());
        Optional<MemberDTO> oq = this.memberRepository.findById("수정된22222");
        assertTrue(oq.isPresent());
        MemberDTO q = oq.get();
        this.memberRepository.delete(q);
        assertEquals(21, this.memberRepository.count());
    }
}