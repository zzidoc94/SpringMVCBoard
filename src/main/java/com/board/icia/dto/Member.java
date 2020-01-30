package com.board.icia.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//@Getter@Setter
@Alias("member")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	private String id;
	private String pw;	
}
