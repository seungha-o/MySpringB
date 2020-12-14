package com.kh.myspringb.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.myspringb.board.model.domain.Board;

@Repository("bDao")
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int listCount() {
		return sqlSession.selectOne("Board.listCount");
	}
	public List<Board> selectOne(){
		return sqlSession.selectOne("Board.selectOne");
	}
	
	public int insertBoard(Board b) {
		return sqlSession.insert("Board.insertBoard", b);
	}
	
	public List<Board> selectList(){
		return sqlSession.selectList("Board.selectList");
	}
	public List<Board> searchList(String keyword) {
		return sqlSession.selectList("Board.searchList", keyword);
	}
	public int addReadCount(String board_num) {
		return sqlSession.update("Board.addReadCount",board_num);
	}
	public int updateBoard(Board b){
		return sqlSession.update("Board.updateBoard", b);
	}
	public int deleteBoard(String board_num) {
		return sqlSession.delete("Board.deletBoard", board_num);
	}
	
}
