package com.mycompany.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dao.Ch14EmployeeDao;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Employee;
import com.mycompany.webapp.service.Ch14BoardService;
import com.mycompany.webapp.service.Ch14EmployeeService;

@Controller
@RequestMapping("/ch14")
public class Ch14Controller {
	//Field------------------------------------------
	private static final Logger logger = 
			LoggerFactory.getLogger(Ch14Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "ch14/content";
	}
	
	//의존 주입
	@Resource
	private DataSource dataSource;
	
	@GetMapping("/conntest")
	public String conntest(Model model) {
		try {
	         // 커넥션 풀에서 커넥션 대여
	         Connection conn = dataSource.getConnection();   // 커넥션 풀에게 요구 (성공시 연결이 된거임)
	         logger.info("연결성공");
	         model.addAttribute("result","연결 성공");
	         // 대여한 커넥션 반납
	         conn.close();
	      } catch (SQLException e) {
	         logger.info("연결실패");
	         model.addAttribute("result","연결 실패");
	         e.printStackTrace();
	      }
		
	      return "ch14/conntest";
	}
	
	@GetMapping("/jsonresponse1")
	public void jsonresponse1(HttpServletResponse response) throws IOException {
		//response의 타입 지정 (json을 보내겠다)
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		/* 원시적인 방법
		pw.println("{");
		pw.println("\"name\":\"홍길동\",");	//속성 : 속성값
		pw.println("\"age\":30,");
		pw.println("\"car\": {\"kind\":\"그랜저\", \"color\":\"흰색\"},");
		pw.println("\"hobby\":[\"영화\", \"여행\", \"수영\"]");
		pw.println("}");*/
		
		JSONObject root = new JSONObject();//json 라이브러리 사용해서 객체
		root.put("name", "홍길동");
		root.put("age", 30);
		
		JSONObject car = new JSONObject();
		car.put("kind", "그랜저");
		car.put("color", "흰색");
		root.put("car", car);
		
		JSONArray hobby = new JSONArray();//json 라이브러리 사용해서 배열
		hobby.put("영화");
		hobby.put("여행");
		hobby.put("수영");
		root.put("hobby", hobby);
		
		String json = root.toString();	//json결과
		
		pw.println(json);
		pw.flush();
		pw.close();
	}
	
	@GetMapping("/jsonresponse2")
	public void jsonresponse2(HttpServletResponse response) throws IOException {
		//response의 타입 지정 (json을 보내겠다)
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		/* 원시적인 방법
		pw.println("[");
		pw.println("{\"bno\":1, \"btitle\":\"제목1\", \"bwriter\":\"글쓴이1\"},");
		pw.println("{\"bno\":2, \"btitle\":\"제목2\", \"bwriter\":\"글쓴이2\"},");
		pw.println("{\"bno\":3, \"btitle\":\"제목3\", \"bwriter\":\"글쓴이3\"}");
		pw.println("]");*/
		JSONArray root = new JSONArray();//json 라이브러리 사용해서 배열
		for(int i = 1 ; i <= 3; i++) {
			JSONObject board = new JSONObject();
			board.put("bno", i);
			board.put("btitle", "제목"+i);
			board.put("bwriter", "글쓴이"+i);
			root.put(board);
		}
		String json = root.toString();
		pw.println(json);
		
		pw.flush();
		pw.close();
	}
	
	@Resource
	private Ch14EmployeeService employService;	
	
	@GetMapping("/employee")
	public String employee(int employee_id) {
		logger.info("실행");
		Ch14Employee emp = employService.getEmployee(employee_id);
		logger.info("employee_id: " + emp.getEmployee_id());
		logger.info("first_name: " + emp.getFirst_name());
		logger.info("last_name: " + emp.getLast_name());
		return "ch14/content";
	}
	
	@GetMapping("/employee2")
	public void employee2(int employee_id, HttpServletResponse response) throws IOException {
		logger.info("실행");
		Ch14Employee emp = employService.getEmployee(employee_id);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		JSONObject root = new JSONObject();
		root.put("eno", emp.getEmployee_id());
		root.put("first", emp.getFirst_name());
		root.put("last", emp.getLast_name());
		
		String json = root.toString();
		pw.println(json);
		pw.flush();
		pw.close();
	}

	@Resource
	private Ch14BoardService boardService;
	
	@GetMapping("/boardlist")
	public String boardlist(Model model) {
		List<Ch14Board> list = boardService.getBoardList();
		model.addAttribute("list", list);
		return "ch14/boardlist";
	}
	
	@GetMapping("/boardsave")
	public String boardsave() {
		/*
		for(int i = 1 ; i <= 100; i++) {
			Ch14Board board = new Ch14Board();
			board.setBtitle("제목"+i);
			board.setBcontent("내용"+i);
			board.setBwriter("winter");
			boardService.saveBoard(board);
		}*/
		
		return "redirect:/ch14/boardlist";
	}
}
