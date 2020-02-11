package com.board.icia.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.board.icia.dto.Bfile;
import com.board.icia.dto.Board;
import com.board.icia.dto.Reply;

public interface IBoardDao {

	List<Board> getBoardList(int pNum);

	Board getContents(Integer bNum);

	List<Reply> getReplyList(Integer bNum);
	
	@Select("select count(*) from blist_1")
	int getBoardCount();

	boolean replyInsert(Reply r);
	
	@Delete("delete from r where r_bnum=#{r_bnum}")
	boolean replyDelete(Integer bNum);
	@Delete("delete from b where b_num=#{b_num}")
	boolean articleDelete(Integer bNum);
	boolean boardWrite(Board board);
	@Insert("insert into bf values(bf_seq.nextval,#{bNum},#{oriFileName},#{sysFileName})")
	boolean fileInsert(Map<String, String> fMap);
	//한 세션안에서 currval을 먼저 쓰면 안됌
	@Select("select board_seq.currval from dual")
	int getCurBoardNum();
	@Select("select * from bf where bf_bnum=#{bNum}")
	List<Bfile> getBfList(Integer bNum);
	@Delete("delete from bf where bf_bnum=#{bNum}")
	boolean fileDelete(Integer bNum);

}
