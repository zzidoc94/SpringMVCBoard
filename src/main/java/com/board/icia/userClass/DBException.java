package com.board.icia.userClass;

public class DBException extends RuntimeException{
	public DBException() {
		super("스프링은 RuntimeException(선택) 예외만 인정합니다. 필수적인 예외가 발생한 경우 RuntimeException 예외로 변환한다.");
	}
}
