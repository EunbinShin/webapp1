package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//http://localhost:8080/webapp1/
	@RequestMapping("/")
	public String home() {
		logger.info("home() 실행");
		return "home";
	}
}
