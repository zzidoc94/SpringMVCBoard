package com.board.icia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.Member;
@Service //@Component
public class MemberManagement {
	@Autowired
	private IMemberDao mDao;

	public ModelAndView access() {
	   Member mb=new Member();
	   mb.setId("bbb");
	   mb.setPw("1111");
	   System.out.println("result="+mDao.getLoginResult(mb));
	   ModelAndView mav=new ModelAndView();
	   mav.addObject("result", mDao.getLoginResult(mb));
	   mav.setViewName("home");
	   return mav;
		
	}
	
}
