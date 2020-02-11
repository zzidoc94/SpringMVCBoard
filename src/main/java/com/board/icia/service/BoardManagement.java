package com.board.icia.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.dao.IBoardDao;
import com.board.icia.dto.Bfile;
import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;
import com.board.icia.userClass.DBException;
import com.board.icia.userClass.FileManager;
import com.board.icia.userClass.Paging;
import com.board.icia.userClass.UploadFile;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BoardManagement {
	@Autowired
	private IBoardDao bDao;
	@Autowired
	private UploadFile upload;
	@Autowired
	private FileManager fm;
	ModelAndView mav;
	public ModelAndView getBoardList(Integer pageNum) {
		mav= new ModelAndView();
		String view=null;
		List<Board> bList=null;
		int pNum=(pageNum==null)?1:pageNum;
		System.out.println("pNum="+pNum);
		
			bList=bDao.getBoardList(pNum);
			if(bList!=null) {
				System.out.println("size="+bList.size());
				mav.addObject("bList",bList);
				mav.addObject("paging",getPaging(pNum));
				view="boardList";
			}else {
				view="home";				
			}
		
		mav.setViewName(view);
		return mav;
	}
	private Object getPaging(int pNum) {
		int maxNum=bDao.getBoardCount(); 		// 전체 글의 개수
		int listCount=10; //10		// 페이지당 나타낼 글의 갯수
		int pageCount=2; //2		// 페이지그룹당 페이지 갯수
		String boardName="boardlist"; 	// 게시판이 여러개일때 url 적으면 됌
		Paging paging=new Paging(maxNum,pNum,listCount,pageCount,boardName);
		return paging.makeHtmlPaging();
	}
	public ModelAndView getContents(Integer bNum) {
		mav=new ModelAndView();
		Board board=bDao.getContents(bNum);
		mav.addObject("board",board);
		List<Reply> rList= bDao.getReplyList(bNum);
		mav.addObject("rList",rList);
		List<Bfile> bfList=bDao.getBfList(bNum);
		mav.addObject("bfList",bfList);
		System.out.println("fsize:"+bfList.size());
		log.info("board:{}",board);
		log.info("rList:{}",rList);
		String view="boardContentsAjax";	//jsp
		
		mav.setViewName(view);
		return mav;
	}
	public String replyInsert(Reply r) {
		String json=null;
		if(bDao.replyInsert(r)) {
			List<Reply> rList=bDao.getReplyList(r.getR_bnum());
			json=new Gson().toJson(rList);
			System.out.println(json);
		}
		return json;
	}
	public Map<String, List<Reply>> replyInsertJackson(Reply r) {
		Map<String, List<Reply>> rMap=null;
		if(bDao.replyInsert(r)) {
			List<Reply> rList=bDao.getReplyList(r.getR_bnum());
			rMap=new HashMap<String, List<Reply>>();
			rMap.put("rList",rList);
			System.out.println("rMap="+rMap);
			System.out.println("rMap="+rMap.get("rList"));
		}
		return rMap;
	}
	// RedirectAttributes는 Redirect전 session영역에 저장한뒤 redirect 후 즉시 삭제한다.
	// 삭제직전 session영역에 저장했던 데이터는 request객체에 저장한다.
	// addFlashAttribute: post방식(session에 저장후 1번 사용하면 삭제함)
	// 화면(view)에서 한번만 사용된다. 새로고침시 사라짐.

	// attr.addAttribute: get방식(session에 저장후 request객체에 저장후 삭제함)
	@Transactional
	public ModelAndView boardDelete(Integer bNum, RedirectAttributes attr) throws DBException {
		System.out.println("bNum=" + bNum);
		mav = new ModelAndView();
		boolean r = bDao.replyDelete(bNum);
		System.out.println("r=" + r);
		List<Bfile> bfList=bDao.getBfList(bNum);
		boolean f= bDao.fileDelete(bNum);
		System.out.println("service list="+bfList);
		fm.delete(bfList);
		System.out.println("f=" + f);
		
		boolean a = bDao.articleDelete(bNum);
		// boolean a=bDao.aticleDelete(1000); //번호가 없어서 실패
		System.out.println("a=" + a);
		
		if (a == false) { // 0개 원글을 삭제한 경우 예외발생시켜서 롤백
			throw new DBException();
		}
		if (r && a && f) {
			System.out.println("댓글 ,파일, 원글 존재시 삭제 트랜잭션 성공");
			attr.addFlashAttribute("bNum", bNum); // post방식
			// attr.addAttribute("bNum", bNum); //get방식으로 request객체에 넘겨준다.
		} else {
			System.out.println("삭제 트랜잭션 실패");
		}
		// mav.addObject("bNum", bNum); //get방식으로 request객체에 넘겨준다.
		mav.setViewName("redirect:boardlist");

		return mav;
	}
		
//	@Transactional
//	public ModelAndView boardDelete(Integer bNum) throws DBException {
//		mav=new ModelAndView();
//		bDao.replyDelete(bNum);
//		boolean a=bDao.articleDelete(bNum);
//		if(!a) {
//			throw new DBException();
//		}
//		if(a) {
//			System.out.println("삭제 트랜젝션 성공");
//		}else {
//			System.out.println("삭제 트랜젝션 실패");
//		}
//		//mav.addObject("bNum",bNum);
//		mav.setViewName("redirect:boardlist");
//		return mav;
//	}
	
	@Transactional
	public ModelAndView boardWrite(MultipartHttpServletRequest multi) {
		mav=new ModelAndView();
		String view=null;
		String title=multi.getParameter("b_title");
		String contents=multi.getParameter("b_contents");
		int check=Integer.parseInt(multi.getParameter("file_Check"));
		System.out.println("check="+check);//첨부:1,
		String id=(String) multi.getSession().getAttribute("id");
		System.out.println("id:"+id);
		
		Board board=new Board();
		board.setB_title(title).setB_contents(contents).setB_id(id);
		boolean b=bDao.boardWrite(board);
		if(b) {
			view="redirect:/boardlist";//url
		}else {
			view="WriteFrm";	//jsp
		}
		boolean f=false;
		if(check==1) {
			f=upload.fileUp(multi,board.getB_num());
			System.out.println("bnum="+board.getB_num());
			if(f) {
				view="redirect:/boardlist";//url
			}else {
				view="WriteFrm";	//jsp
			}
		}
		
		
		mav.setViewName(view);
		return mav;
	}
	
}
