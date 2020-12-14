package com.kh.myspringb.board.controller;

import java.io.File;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.myspringb.board.model.domain.Board;
import com.kh.myspringb.board.model.sevice.BoardService;


//url에 뭐가 들어오는지 결정, 어떤 페이지를 보여줄지 view를 결정, service에 있는 이름을 호출

@Controller
public class BoardController {
	@Autowired
	private BoardService bService; // impl에있는 service호출

//게시글 작성 페이지
	@RequestMapping(value = "/writeForm.do", method = RequestMethod.GET)
	public String boardInsertForm(ModelAndView mv) {
		return "board/writeForm"; // view 페이지에서 작성후 form action = "bInsert.do"로 들어오도록함.
	}

//작성된 글을 bInsert.do에 insert
	@RequestMapping(value = "/bInsert.do", method = RequestMethod.POST)
	public ModelAndView boardInsert(Board b, @RequestParam(name = "upfile") MultipartFile report, HttpServletRequest request, ModelAndView mv) {
		//@RequestParam(name = "upfile") MultipartFile report 같은 거임 Multipartfile report = request.getparameter("upfile")
		//첨부파일 저장
		if (report!=null && !report.equals("")) {
			saveFile(report, request);
		}
		b.setBoard_file(report.getOriginalFilename());//저장된 파일명을 vo에 set
		
		
		bService.insertBoard(b); // <-이게 성공적이라면 set함
		//
		mv.setViewName("redirect:bList.do"); // 그럼 이게 게시글 리스트 select로 이동(view로 이동하는 것이 아니라 controller url중 게시글 리스트로
												// 이동하는것임
		// 실패했다면 에러페이지
		mv.setViewName("errorPage");
		return mv;
	}

//게시글 리스트 select
	@RequestMapping(value = "/bList.do")
	public ModelAndView boardListService(ModelAndView mv) {
		// bService.selectList()
		// 얘를 받아서 object에 바로 실어주기
		mv.addObject("list", bService.selectList());
		mv.setViewName("board/blist"); // 게시글 리스트 보여주는 페이지를 채워줌 board/blist.jsp가 보여질것임
		return mv;
	}
	
	private void saveFile ( MultipartFile report, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		 String savePath = root + "\\uploadFiles";
		 File folder = new File(savePath);
		 if (!folder.exists()) {
		 folder.mkdir(); // 폴더가 없다면 생성한다.
		 }
		 String filePath = null;
		 try {
		 // 파일 저장
		 System.out.println(report.getOriginalFilename() + "을 저장합니다.");
		 System.out.println("저장 경로 : " + savePath);
		 filePath = folder + "\\" + report.getOriginalFilename();
		 report.transferTo(new File(filePath)); // 파일을 저장한다
		 System.out.println("파일 명 : " + report.getOriginalFilename());
		 System.out.println("파일 경로 : " + filePath);
		 System.out.println("파일 전송이 완료되었습니다.");
		 } catch (Exception e) {
		 System.out.println("파일 전송 에러 : " + e.getMessage());
		 }
	}
}
