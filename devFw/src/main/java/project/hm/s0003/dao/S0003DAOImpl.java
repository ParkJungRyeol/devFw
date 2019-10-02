package project.hm.s0003.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.s0003.vo.S0003VO;

@Repository("s0003DAO")
public class S0003DAOImpl implements S0003DAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<S0003VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<S0003VO> list = sqlSession.selectList("hm.s0003.searchList", searchMap);
		return list;
	}

	@Override
	public void insertData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hm.s0003.insertData", row);
	}

	@Override
	public void updateData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hm.s0003.updateData", row);
	}

	@Override
	public void deleteData(Map<String, String> row) throws DataAccessException {
		sqlSession.update("hm.s0003.deleteData", row);
	}
}