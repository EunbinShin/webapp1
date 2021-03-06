package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch14BoardDao;
import com.mycompany.webapp.dao.Ch14EmployeeDao;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Employee;

@Service
public class Ch14BoardService {
	private static final Logger logger = 
			LoggerFactory.getLogger(Ch14BoardService.class);
	
	@Resource
	public Ch14BoardDao boardDao;
	
	public List<Ch14Board> getBoardList(){
		List<Ch14Board> list = boardDao.selectAll();
		return list;
	}
	
	public void saveBoard(Ch14Board board) {
		boardDao.insert(board);
	}
}
