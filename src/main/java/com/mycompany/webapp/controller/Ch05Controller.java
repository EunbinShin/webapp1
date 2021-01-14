package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ch05")
public class Ch05Controller {
	private static final Logger logger = 
			LoggerFactory.getLogger(Ch05Controller.class);
	
	@GetMapping("/content")
	public String content(){
		logger.info("실행");
		return "ch05/content";
	}
	
	@GetMapping("/method1")
	public String method1(@RequestHeader("User-Agent") String userAgent) {
		logger.info("실행");
		//브라우저 구분
		if(userAgent.contains("Edg")) {
			logger.info("엣지 브라우저");
		}else if(userAgent.contains("Chrome")){
			logger.info("크롬 브라우저");
		}else if(userAgent.contains("Trident/7.0")){
			logger.info("익스플로러 11");
		}else if(userAgent.contains("MSIE")){
			logger.info("익스플로러 10이하");
		}else {
			logger.info("이외의 브라우저");
		}
		return "ch05/content";
	}
	
	@GetMapping("/method2")
	public String method2(HttpServletRequest request) {
		logger.info("실행");
		String userAgent = request.getHeader("User-Agent");
		//브라우저 구분
		if(userAgent.contains("Edg")) {
			logger.info("엣지 브라우저");
		}else if(userAgent.contains("Chrome")){
			logger.info("크롬 브라우저");
		}else if(userAgent.contains("Trident/7.0")){
			logger.info("익스플로러 11");
		}else if(userAgent.contains("MSIE")){
			logger.info("익스플로러 10이하");
		}else {
			logger.info("이외의 브라우저");
		}
		return "ch05/content";
	}
}
