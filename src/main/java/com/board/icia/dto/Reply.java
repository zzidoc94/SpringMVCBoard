package com.board.icia.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

@Alias("reply")
@Accessors(chain=true)
@Data
public class Reply {
	private int r_bnum;	//원글 번호
	private int r_num;	//댓글 번호
	private String r_contents;	//댓글 내용
	private Timestamp r_date;	//작성 날짜
	private String r_id;	//작성자
}
