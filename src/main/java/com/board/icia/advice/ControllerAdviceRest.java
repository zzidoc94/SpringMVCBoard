package com.board.icia.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.board.icia.exception.CommonException;
import com.board.icia.exception.IdCheckException;




@RestControllerAdvice
public class ControllerAdviceRest {
	//한글 깨짐 방지
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/plain;charset=utf-8");
		return headers;
	}
	
	@ExceptionHandler(CommonException.class)
	public ResponseEntity<?> replyExceptionHandler(){
		System.out.println("RestController Advice");
		return ResponseEntity.status(HttpStatus.CONFLICT).body("noGetReply");
	}
	@ExceptionHandler(IdCheckException.class)
	public ResponseEntity<?> UserNotFoundExceptionHandler() {
		return new ResponseEntity<String>("사용불가 아이디입니다.", getHeaders(),HttpStatus.BAD_REQUEST);
	}
	
}
