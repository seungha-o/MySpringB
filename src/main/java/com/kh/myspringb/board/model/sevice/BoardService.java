package com.kh.myspringb.board.model.sevice;

import java.util.List;

import com.kh.myspringb.board.model.domain.Board;

public interface BoardService {

	public int listCount() ;
	public List<Board> selectOne();
	public int insertBoard(Board b);
	public List<Board> selectList();
	public List<Board> searchList(String keyword) ;
	public int addReadCount(String board_num) ;
	public int updateBoard(Board b);
	public int deleteBoard(String board_num) ;
}
	
