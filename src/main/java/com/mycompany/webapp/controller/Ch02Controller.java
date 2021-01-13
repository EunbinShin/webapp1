package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch02")
public class Ch02Controller {
	private static final Logger logger = 
			LoggerFactory.getLogger(Ch01Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		logger.info("ch02 실행");
		return "ch02/content";
	}
	
	@RequestMapping("/request1")	//GET과 POST를 둘다 지원
	//@RequestMapping(value="/request1",method = RequestMethod.GET)	//GET 방식으로 요청해야만 실행 가능하다
	//@RequestMapping(value="/request1",method = RequestMethod.POST)	//POST 방식으로 요청해야만 실행 가능하다
	public String request1() {
		logger.info("ch02 실행");
		return "ch02/content";
	}
	
	@GetMapping("/getMethod")	//GET 방식으로 요청해야만 실행 가능하다
	public String getMethod() {
		logger.info("get 실행");
		return "ch02/content";
	}
	
	@PostMapping("/postMethod")	//POST 방식으로 요청해야만 실행 가능하다
	public String postMethod() {
		logger.info("post 실행");
		return "ch02/content";
	}
	
	//회원 가입 폼을 달라
	@GetMapping("/join")
	public String joinForm() {
		logger.info("회원가입 양식을 보냄");
		return "ch02/joinForm";
	}
	
	//회원 가입을 시켜달라
	@PostMapping("/join")
	public String join() {
		logger.info("회원가입 처리함");
		return "ch02/content";
	}
	
	//회원 가입 폼을 달라(비동기)
	@GetMapping("/joinAsync")
	public String joinFormAsync() {
		logger.info("회원가입 양식을 보냄");
		return "ch02/joinFormAsync";
	}
}
