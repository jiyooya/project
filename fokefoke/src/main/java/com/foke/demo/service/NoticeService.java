package com.foke.demo.notice;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository NoticeRepository;
    
    //공지사항 목록
    public List<NoticeDTO> getList() {
        return this.NoticeRepository.findAll();
    }
    
    //공지사항 상세조회
    public NoticeDTO getnoticedto(Integer id) {  
        Optional<NoticeDTO> notice = this.NoticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        } else {
            throw new DataNotFoundException("Notice not found");
        }
    }
    
    //게시글쓰기
    @Transactional
    public void enroll(String noticeTitle, String noticeContent, String imageUrl) {
        NoticeDTO notice = new NoticeDTO();
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        notice.setNoticeImage(imageUrl);

        NoticeRepository.save(notice);
    }
    

    //페이징
    public Page<NoticeDTO> getList(int page) {
    	List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("noticeDate"));
        Pageable pageable = PageRequest.of(page, 6, Sort.by(sorts));
        return this.NoticeRepository.findAll(pageable);
    }
    //게시글 수정
    public void modify(NoticeDTO noticedto, String noticeTitle, String noticeContent) {
    	noticedto.setNoticeTitle(noticeTitle);
    	noticedto.setNoticeContent(noticeContent);
        this.NoticeRepository.save(noticedto);
    }
    
    //게시글 삭제
    public void delete(Integer id) {
        NoticeRepository.deleteById(id);
    }
  
    //이미지 업로드
    /*MultipartFile file 추가*//*예외처리*/
//    public void imageupload(NoticeDTO notice , MultipartFile file) throws Exception{
//        /*우리의 프로젝트경로를 담아주게 된다 - 저장할 경로를 지정*/
//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\blog";
//
//        /*식별자 . 랜덤으로 이름 만들어줌*/
//        UUID uuid = UUID.randomUUID();
//
//        /*랜덤식별자_원래파일이름 = 저장될 파일이름 지정*/
//        String fileName = uuid + "_" + file.getOriginalFilename();
//
//        /*빈 껍데기 생성*/
//        /*File을 생성할건데, 이름은 "name" 으로할거고, projectPath 라는 경로에 담긴다는 뜻*/
//        File saveFile = new File(projectPath, fileName);
//
//        file.transferTo(saveFile);
//
//        /*디비에 파일 넣기*/
//        notice.setNoticeImage(fileName);
//        /*저장되는 경로*/
//        notice.setNoticeImagefile("/image/blog/" + fileName); /*저장된파일의이름,저장된파일의경로*/
//        
//        /*파일 저장*/
//        NoticeRepository.save(notice);
//    }
}