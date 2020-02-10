package com.board.icia.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;

@Alias("reply")
@Accessors(chain=true)
@Data
public class Reply {
	private int r_bnum;	//원글 번호
	private int r_num;	//댓글 번호
	private String r_contents;	//댓글 내용
	//property 에디터: 클라이언트에서 서버로 데이터를 넘길때 사용
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
	@DateTimeFormat(pattern="yyyy/MM/dd hh:mm:ss")
	private Timestamp r_date;	//작성 날짜
	private String r_id;	//작성자
}
