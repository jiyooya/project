package com.foke.demo.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeDTO, Integer> {

    NoticeDTO findByNoticeTitle(String noticeTitle);

    NoticeDTO findByNoticeContent(String noticeContent);

    
    List<NoticeDTO> findByNoticeTitleLike(String noticeTitle);

    List<NoticeDTO> findAll();

    Page<NoticeDTO> findAll(Pageable pageable);

}
