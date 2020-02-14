package com.board.icia;



import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dto.Member;
import com.board.icia.service.MemberManagement;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	

	@Autowired
	private MemberManagement mm;
	private ModelAndView mav;
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		//mm=new MemberManagement(); //protoType
		mav= new ModelAndView();
		logger.info("로그인 화면으로 이동 + 대신에","콤마로 표시함");
		mav.setViewName("home");	//로그인 화면
		return mav;
	}
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public ModelAndView access(Member mb,HttpServletRequest req) {
		mav= mm.memberAccess(mb,req);
		return mav;
	}
	@RequestMapping(value = "/joinfrm", method = RequestMethod.GET)
	public ModelAndView joinFrm() {
		mav= new ModelAndView();
		mav.setViewName("joinFrm");
		
		return mav;
	}
	@RequestMapping(value = "/memberjoin", method = RequestMethod.POST)
	public ModelAndView memberJoin(Member mb) {
		
		mav=mm.memberJoin(mb);
		
		return mav;
	}
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpServletRequest req) {
		mav=new ModelAndView();
		req.getSession().invalidate();
		mav.setViewName("redirect:home");
		return mav;
	}
	@GetMapping(value="/member/{dept}/{emp}")
	public String pathVariable(@PathVariable int dept,@PathVariable String emp) {
		System.out.println("dept="+dept);
		System.out.println("emp="+emp);
		return "home";
	}
	
	
}
