package com.board.icia.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

@Alias("board")
@Data
@Accessors(chain=true)
public class Board {
	private int b_num;
	private String b_title;
	private String b_contents;
	private String b_id;
	private String b_name;
	private Timestamp b_date;
	private int b_views;
}
