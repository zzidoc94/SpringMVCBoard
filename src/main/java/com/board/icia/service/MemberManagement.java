package com.board.icia.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dao.IMemberDao;
import com.board.icia.dto.Member;
import com.board.icia.exception.IdCheckException;

@Service // @Component
public class MemberManagement {
	@Autowired
	private IMemberDao mDao;
	private ModelAndView mav;

	private void hashMapTest(String id, String pwd) {
		Map<String, String> hMap = new HashMap<>();
		hMap.put("id", id);
		hMap.put("pw", pwd);
		boolean result = mDao.hashMapTest(hMap);
		System.out.println("result=" + result);
		hMap = mDao.hashMapTest2(id);
		System.out.println("name=" + hMap.get("m_name"));
		System.out.println("name=" + hMap.get("g_name"));
	}

	public ModelAndView memberAccess(Member mb, HttpServletRequest req) {
		mav = new ModelAndView();
		String view = null;
		// 스프링에선 복호화 메소드 없음
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String pwdEncode = mDao.getSecurityPwd(mb.getM_id());
		System.out.println("pw=" + pwdEncode);
		hashMapTest(mb.getM_id(), pwdEncode);
		if (pwdEncode != null) {
			if (pwdEncoder.matches(mb.getM_pwd(), pwdEncode)) { // 비교
				// 로그인 성공
				HttpSession session = req.getSession();
				session.setAttribute("id", mb.getM_id());

				mb = mDao.getMemberInfo(mb.getM_id());
				session.setAttribute("mb", mb);
				// mav.addObject("mb",mb);
				// view="boardList"; //jsp로
				// view="forward:/boardlist";//forward:url,POST-POST,GET-GET끼리만
				view = "redirect:/boardlist";// redirect:url,POST,GET------>GET방식으로만

				// view="forward:/boardList"; //foward:url
			} else { // 비밀번호 오류
				mav.addObject("check", "2");
				view = "home";
			}
		} else { // 아이디 오류
			mav.addObject("check", "2");
			view = "home";
		}
		mav.setViewName(view);
		return mav;
	}

	public ModelAndView memberJoin(Member mb) {
		mav = new ModelAndView();
		String view = null;
		// Encoder(암호화)<--->Decoder(복호화)
		// 스프링은 암호화는 가능하지만 복호화가 불가능하다.
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		// 비밀번호만 암호화해서 DB에 저장
		mb.setM_pwd(pwdEncoder.encode(mb.getM_pwd()));
		if (mDao.memberJoin(mb)) {
			view = "home";// 회원가입 성공
			mav.addObject("check", 1);
		} else {
			view = "joinFrm";
			mav.addObject("check", 0);
		}
		mav.setViewName(view);

		return mav;
	}

//	public boolean idAvailable(String m_id) {
//		Member mb=mDao.getMemberInfo(m_id);
//		if(mb==null) return true;	//아이디를 사용할 수 있다.
//		else return false;			//아이디를 사용할 수 없다.
//	}
	public String idAvailable(String m_id) {
		Member mb = mDao.getMemberInfo(m_id);
		if (mb != null)
			throw new IdCheckException("사용불가 아이디입니다.");
		return "사용가능한 아이디입니다.";
	}
}
