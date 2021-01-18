package com.mycompany.webapp.service;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.mycompany.webapp.dao.*;

@Service
public class Ch13Service7 {
	private static final Logger logger = 
			LoggerFactory.getLogger(Ch13Service7.class);
	
	@Value("${fileupload}")
	private String saveDirPath;
	
	public void method() {
		logger.info(saveDirPath+"실행");
	}
}
