package com.foke.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foke.demo.dto.NoticeDTO;

public interface NoticeRepository extends JpaRepository<NoticeDTO, Integer>{
	
	NoticeDTO findByNoticeTitle(String noticeTitle);
	NoticeDTO findByNoticeContent(String noticeContent);
    List<NoticeDTO> findByNoticeTitleLike(String noticeTitle);
    List<NoticeDTO> findAll();
}
