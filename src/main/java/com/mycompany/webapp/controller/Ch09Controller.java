package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09User;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = 
			LoggerFactory.getLogger(Ch09Controller.class);

	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch09/content";
	}
	
	/*
	@PostMapping("/fileupload")
	public String fileupload(Ch09User photo) {
		//문자 파트 정보 얻기
		String title = photo.getTitle();
		String desc = photo.getDesc();
		logger.info("title: "+title);
		logger.info("desc: "+desc);
		//파일 파트 정보 얻기
		MultipartFile attach = photo.getAttach();
		String originalFileName = attach.getOriginalFilename();
		String contentType = attach.getContentType();
		long size = attach.getSize();
		logger.info("originalFileName: "+originalFileName);
		logger.info("contentType: "+contentType);
		logger.info("size: "+size);
		return "redirect:/ch09/content";
	}*/
	
	@PostMapping("/photoupload")
	public String photoUpload(Ch09User user) {
		//문자 파트 정보 얻기
		String uid = user.getUid();
		String uname = user.getUname();
		String upassword = user.getUpassword();
		logger.info("uid: "+uid);
		logger.info("uname: "+uname);
		logger.info("upassword: "+upassword);
		
		//파일 파트 정보 얻기
		MultipartFile uphoto = user.getUphoto();
		if(!uphoto.isEmpty()) {
			String originalFileName = uphoto.getOriginalFilename();
			String contentType = uphoto.getContentType();
			long size = uphoto.getSize();
			logger.info("originalFileName: "+originalFileName);
			logger.info("contentType: "+contentType);
			logger.info("size: "+size);
			
			//파일경로
			String saveDirPath = "D:/MyWorkspace/uploadfiles/";
			//파일 이름
			String fileName = new Date().getTime()+"_"+originalFileName;
			//파일 전체 경로
			String filePath = saveDirPath + fileName;
			File file = new File(filePath);
			try {
				uphoto.transferTo(file);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:/ch09/content";
	}
	
	@GetMapping("/photolist")
	public String photoList(Model model) {
		logger.info("실행");
		String saveDirPath = "D:/MyWorkspace/uploadfiles/";
		File dir = new File(saveDirPath);
		String[] fileNames = dir.list();
		model.addAttribute("fileNames", fileNames);
		return "ch09/photolist";
	}
	
	@GetMapping("/photodownload")
	public void photoDownload(String photo,  HttpServletResponse response) {
		logger.info("실행");
		//응답이 없으면 임의로 jsp응답을 만들기 때문에 응답을 내보내야됨(HttpServletResponse사용)
		//보내고자하는 응답이 뭔지 알려주는 메소드, uphoto.getContentType()을 db에 저장해야하는 이유
		response.setContentType("image/jpeg");	//응답 본문 데이터의 종류를 응답 헤더에 추가 response.setHeader("content-type", "image/jpeg";
		
		//응답 본문 데이터를 파일로 다운로드할 수 있도록 응답 헤더에 추가
		try {
			//HTTP 규약에 따라 헤더에 한글을 넣지 못함 그래서 UTF-8을 다시 ISO-8859-1로 변환
			photo = new String(photo.getBytes("UTF-8"), "ISO-8859-1");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment; filename=\""+photo+"\"");	//attachment가 들어가면 contents가 다운로드됨
		
		try {
			String saveDirPath = "D:/MyWorkspace/uploadfiles/";
			String filePath = saveDirPath+photo;	//실제 경로
			
			InputStream is = new FileInputStream(filePath);
			OutputStream os = response.getOutputStream();
			
			/*
			while(true) {
				byte[] data = new byte[1024];
				int readByteNum = is.read(data);
				if(readByteNum == -1) break;
				os.write(data, 0, readByteNum);	//초과해서 출력하지 않도록 제한 -> readByteNum만큼 만 출력
			}*/
			
			FileCopyUtils.copy(is, os);
			
			os.flush();
			os.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
