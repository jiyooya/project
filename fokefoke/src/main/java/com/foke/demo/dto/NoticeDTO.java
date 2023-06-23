package com.foke.demo.dto;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class NoticeDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noticeId;
	@Column(length = 30 )
	private String noticeTitle;
	@Column(length = 200)
	private String noticeContent;
	@CreatedDate
	private Date noticeDate;
	@Column(length = 100)
	private String noticeImage;
	@Column(length = 10)
	private String noticeState;

		
	}



