package com.mycompany.webapp.service;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.mycompany.webapp.dao.*;

@Service
public class Ch13Service6 {
	private static final Logger logger = 
			LoggerFactory.getLogger(Ch13Service6.class);
	//구현객체를 자동으로 넣기에 애매하다 -> 이름으로 주입
	
	//방법1. 리소스로 대입
	@Resource(name = "ch13BoardDao5")
	//@Autowired
	//@Qualifier("Ch13BoardDao5Qulifier")
	private Ch13BoardDao board5;
	
	//방법2. Autowired로 대입
	//@Resource(name = "ch13BoardDao6")
	@Autowired
	@Qualifier("Ch13BoardDao6Qulifier")
	private Ch13BoardDao board6;
	
	public void method() {
		logger.info("실행");
		board5.insert();
		board6.insert();
	}
}
