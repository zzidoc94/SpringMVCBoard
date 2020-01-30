package com.board.icia;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.Member;
import com.board.icia.service.MemberManagement;

@Controller
public class HomeController {
	
	
	
	@Autowired
	private MemberManagement mm;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		//mm=new MemberManagement(); //protoType
		ModelAndView mav=mm.access();
		
		return mav;
	}
	
}
