package com.board.icia;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dto.Reply;
import com.board.icia.service.BoardManagement;
import com.google.gson.Gson;

//@Controller
@RestController	//@ResponseBody 생략가능
@RequestMapping(value="/rest")//공통 url
public class BoardRestController {
	@Autowired
	private BoardManagement bm;
	ModelAndView mav;
	//gson 버전
//	@RequestMapping(value="/replyinsert",produces = "text/plain;charset=UTF-8")
//	//서블릿에서 사용방식을 @ResponseBody가 대신한다.
//	public /* @ResponseBody */ String replyInsert(/* @RequestBody */ Reply r,HttpServletRequest req) {	//@RequestBody: json으로 받을 때 사용
//		System.out.println("r_bnum:"+r.getR_bnum());
//		System.out.println("r_contents:"+r.getR_contents());
//		
//		//String json=bm.replyInsert(r);
//		r.setR_id(req.getSession().getAttribute("id").toString());
//		String json=bm.replyInsert(r);
//		return json;
//	}
	//jackson 버전
	@RequestMapping(value="/replyinsert",produces = "application/json;charset=UTF-8")
	//서블릿에서 사용방식을 @ResponseBody가 대신한다.
	public @ResponseBody Map<String,List<Reply>> replyInsert(/* @RequestBody */ Reply r,HttpServletRequest req) {	//@RequestBody: json으로 받을 때 사용
		r.setR_id(req.getSession().getAttribute("id").toString());
		Map<String,List<Reply>> rMap=bm.replyInsertJackson(r);
		return rMap;	//jackson의 역할:Map--->json으로 변환
		//기본 메시지 컨버터는 기능이 많지 않다. 그래서 gson을 사용하는 게 좋음
		//{'rList',rList}	-->   {"rList":"[{},{},{}]"}
	}
//	@PostMapping(value="/boardwrite")
//	public String boardWrite(Board board, List<MultipartFile> files) {
//		System.out.println("title:"+board.getB_title());
//		System.out.println("contents:"+board.getB_contents());
//		System.out.println("file="+files.get(0).getOriginalFilename());
//		
//		return new Gson().toJson(files);
//	}
	@PostMapping(value="/boardwrite")
	public String boardWrite(MultipartHttpServletRequest multi) {
		System.out.println("title:"+multi.getParameter("b_title"));
		System.out.println("fileCheck:"+multi.getParameter("fileCheck"));
		List<MultipartFile> files=multi.getFiles("files");
		for(int i=0;i<files.size();i++) {
			System.out.println("file="+files.get(i).getOriginalFilename());
		}
		
		return new Gson().toJson(files);
	}
	
}
