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
	private String m_id;
	private String m_pwd;	
	private String m_name;	
	private String m_birth;	
	private String m_addr;	
	private String m_phone;	
	private int m_point;
	
	private String g_name;	//등급 이름
}
