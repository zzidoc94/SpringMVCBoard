package com.board.icia;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.exception.DBException;
import com.board.icia.service.BoardManagement;
import com.board.icia.userClass.FileManager;

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
	@RequestMapping(value="/boarddelete")
	public ModelAndView boardDelete(Integer bNum,RedirectAttributes attr) throws DBException {
		mav=bm.boardDelete(bNum,attr);
		//RedirectAttributes는 Redirect 전 session 영역에 저장한 뒤 redirect 후 즉시 삭제한다
		//삭제 직전 session 영역에 저장했던 데이터는 request 객체에 저장한다.
		//attr.addFlashAttribute(): post방식(session에 저장 후 1번 사용하면 삭제함)
		//attr.addAttribute(): get방식(session에 저장 후 request객체에 저장 후 삭제함)
		attr.addFlashAttribute("bNum",bNum);//무조건 삭제 후
		return mav;
	}
	//화면 전환
	@RequestMapping(value="/writefrm")
	public String writefrm() {
		return "writeFrm";
	}
	@PostMapping(value="/boardwrite")
	public ModelAndView boardWrite(MultipartHttpServletRequest multi){
		//1개의 file Tag를 이용해서 여러 파일을 첨부할때
		mav= new ModelAndView();
		mav=bm.boardWrite(multi);
		return mav;
	}
	@RequestMapping(value="/download")
	public void download(String sysFileName, String oriFileName, HttpServletResponse resp) {
		System.out.println("oriFileName:"+oriFileName);
		String full="E:/springwork/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SpringMVCBoard/upload/"+sysFileName;
		FileManager fm=new FileManager();
		try {
			fm.download(full, oriFileName, resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@GetMapping(value="/test")
	public ModelAndView myBatisTest(String cName, Integer search) {
			mav=bm.myBatisTest(cName,search);
			
		return mav; 
	}
}
