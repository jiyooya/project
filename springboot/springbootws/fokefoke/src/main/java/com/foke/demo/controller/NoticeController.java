package com.foke.demo.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.foke.demo.dto.NoticeDTO;
import com.foke.demo.service.NoticeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/notice")
public class NoticeController {

	private final NoticeService noticeService;

	//목록
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<NoticeDTO> paging = this.noticeService.getList(page);
		model.addAttribute("paging", paging);

		return "notice/Notice_list";
	}
	
	//디테일
	@GetMapping(value = "/detail/{noticeId}")
	public String detail(Model model, @PathVariable("noticeId") Integer id) {
		NoticeDTO noticedto = this.noticeService.getnoticedto(id);
		model.addAttribute("noticedto", noticedto);
		return "notice/Notice_detail";
	}
	
	//글등록
	@GetMapping("/enroll")
	public String noticeEnroll(Model model) {
	    model.addAttribute("noticeDTO", new NoticeDTO());
	    return "notice/Notice_enroll";
	}

	//이미지,글등록
	@PostMapping("/enroll")
	public String noticeEnroll(@Valid NoticeDTO noticedto, BindingResult bindingResult, @RequestParam(value = "fileItem", required = false) MultipartFile imageFile) throws IOException {
	    if (bindingResult.hasErrors()) {
	        return "notice_enroll";
	    }

	    // 이미지 파일 저장
	    String uploadDirectory = "D://kdigital//sbws//fokefoke//src//main//resources//static//img//blog//";
	    if (imageFile != null && !imageFile.isEmpty()) {
	        File uploadDir = new File(uploadDirectory);
	        if (!uploadDir.exists()) uploadDir.mkdirs();

	    }
	    String fileName = imageFile.getOriginalFilename();
	    
	    /* uuid 적용 파일 이름 */
	    String uuid = UUID.randomUUID().toString();
	    fileName = uuid + "_" + fileName;
	    File imageFileToSave = new File(uploadDirectory + fileName);
	    imageFile.transferTo(imageFileToSave);
	  
	    // 파일 URL을 DTO 객체에 설정
	    noticedto.setNoticeImage(fileName);

	    this.noticeService.enroll(noticedto.getNoticeTitle(), noticedto.getNoticeContent(), noticedto.getNoticeImage());
	    return "redirect:/notice/list";
	}

	// 섬네일 데이터 전송하기
	@GetMapping("/display/{noticeImage}")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(@PathVariable("noticeImage") String fileName) { // 특정 파일의 이름을 받아서 이미지 데이터를 전송하는 코드
	    System.out.println("fileName : " + fileName); // fileName은 파일의 경로

	    File file = new File("D://kdigital//sbws//fokefoke//src//main//resources//static//img//blog//" + fileName);

	    System.out.println("file : " + file);

	    ResponseEntity<byte[]> result = null;

	    try {
	        HttpHeaders header = new HttpHeaders();

	        header.add("Content-Type", Files.probeContentType(file.toPath()));
	        result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return result; //event/display?fileName=P1234.png 경로 참조
	}

	
	
  //게시글수정
    @GetMapping("/modify/{id}")
    public String Modify(Model model, @PathVariable("id") Integer id) {
    	model.addAttribute("noticedto", noticeService.getnoticedto(id));
    	
        return "notice/Notice_modify";
    }

    
    //게시글수정실행
    @PostMapping("/modify/{id}")
    public String noticeModify(@PathVariable("id") Integer id, NoticeDTO notice) {
      NoticeDTO noticeTemp = noticeService.getnoticedto(id);
            
      noticeTemp.setNoticeTitle(notice.getNoticeTitle());
      noticeTemp.setNoticeContent(notice.getNoticeContent());
      noticeService.modify(noticeTemp, notice.getNoticeTitle(), notice.getNoticeContent());
      return "redirect:/notice/detail/" + noticeTemp.getNoticeId();
    }
    
    //게시글 삭제
    @PostMapping("/delete/{id}")
    public String noticeDelete(@PathVariable("id") Integer id) {
        noticeService.delete(id);
        return "redirect:/notice/list";
    }
    
 
    
   
}
