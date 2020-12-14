package com.kh.myspringb.board.model.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.myspringb.board.model.dao.BoardDao;
import com.kh.myspringb.board.model.domain.Board;

@Service("bService")
public class BoardImpl implements BoardService {
	@Autowired 
	private BoardDao bDao;
	@Override
	public int listCount() {
		return bDao.listCount();
	}

	@Override
	public int insertBoard(Board b) {
		return bDao.insertBoard(b);
	}

	@Override
	public List<Board> selectList() {
		return bDao.selectList();
	}

	@Override
	public List<Board> selectOne() {
		return bDao.selectOne();
	}

	@Override
	public List<Board> searchList(String keyword) {
		return bDao.searchList(keyword);
	}

	@Override
	public int addReadCount(String board_num) {
		return bDao.addReadCount(board_num);
	}

	@Override
	public int updateBoard(Board b) {
		return bDao.updateBoard(b);
	}

	@Override
	public int deleteBoard(String board_num) {
		return bDao.deleteBoard(board_num);
	}


}
