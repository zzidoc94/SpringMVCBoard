package com.board.icia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.service.BoardManagement;

@Controller
public class BoradContoller {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private BoardManagement bm;		//게시판 서비스 클래스
	private ModelAndView mav;
	@RequestMapping(value = "/boardlist", method = RequestMethod.GET)
	public ModelAndView boardList(Integer pageNum) {	//null 값도 받으려고
		logger.info("/boardlist");
		//mm=new MemberManagement(); //protoType
		mav= bm.getBoardList(pageNum);
		return mav;
	}
	@RequestMapping(value = "/contents")
	public ModelAndView getContents(Integer bNum) {	//null 값도 받으려고
		logger.info("/contents");
		//mm=new MemberManagement(); //protoType
		mav= bm.getContents(bNum);
		return mav;
	}
}
