package com.board.icia.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dao.IBoardDao;
import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;
import com.board.icia.userClass.DBException;
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
	@Transactional
	public ModelAndView boardDelete(Integer bNum) throws DBException {
		mav=new ModelAndView();
		bDao.replyDelete(bNum);
		boolean a=bDao.articleDelete(bNum);
		if(!a) {
			throw new DBException();
		}
		if(a) {
			System.out.println("삭제 트랜젝션 성공");
		}else {
			System.out.println("삭제 트랜젝션 실패");
		}
		//mav.addObject("bNum",bNum);
		mav.setViewName("redirect:boardlist");
		return mav;
	}
	
	@Transactional
	public ModelAndView boardWrite(MultipartHttpServletRequest multi) {
//		List<MultipartFile> files=multi.getFiles("files");
//		System.out.println("파일 개수:"+files.size());
//		for(int i=0;i<files.size();i++) {
//			String f=files.get(i).getOriginalFilename();
//			System.out.println(f);
//		}
		
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
