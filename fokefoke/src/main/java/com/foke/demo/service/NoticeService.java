package com.foke.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foke.demo.DataNotFoundException;
import com.foke.demo.dto.NoticeDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository NoticeRepository;
    
    public List<NoticeDTO> getList() {
        return this.NoticeRepository.findAll();
    }
    //게시판 조회
    public NoticeDTO getnoticedto(Integer id) {  
        Optional<NoticeDTO> question = this.NoticeRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("Notice not found");
        }
    }
}