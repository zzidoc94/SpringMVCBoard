package com.board.icia.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.icia.exception.CommonException;


@ControllerAdvice
public class ControllerAdviceMVC {
	@ExceptionHandler(CommonException.class)
	public String except(CommonException ex, RedirectAttributes attr) {
		System.out.println("메시지:"+ex.getMessage());
		attr.addFlashAttribute("msg",ex.getMessage());
		return "redirect:/boardlist";
	}
}
