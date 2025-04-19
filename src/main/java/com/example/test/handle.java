package com.example.test;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class handle {
	@ExceptionHandler(value = Exception.class)
	public String a(Exception e) {
		return "fail";
	}
}
