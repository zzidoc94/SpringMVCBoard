package com.board.icia.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.experimental.Accessors;

@Alias("bfile")
@Data
@Accessors(chain=true)
public class Bfile {
	private String bf_oriName;
	private String bf_sysName;
}
