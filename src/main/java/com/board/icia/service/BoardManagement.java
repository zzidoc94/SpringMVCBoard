package com.board.icia.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.board.icia.dao.IBoardDao;
import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;
import com.board.icia.userClass.Paging;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BoardManagement {
	@Autowired
	private IBoardDao bDao;
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
	
}
