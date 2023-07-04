package com.foke.demo.notice;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notice")
public class NoticeDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noticeId;

    @Column(length = 30)
    private String noticeTitle;

    @Column(length = 200)
    private String noticeContent;

    @CreationTimestamp
    private LocalDateTime noticeDate;
    
    @Column(name = "noticeImage")
    private String noticeImage;

    @Column(length = 100)
    private String noticeImagefile;

    @Column(length = 10)
    private String noticeState;


}