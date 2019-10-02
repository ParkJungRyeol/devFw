package project.hm.p0003.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.p0003.vo.P0003VO;

@Repository("p0003DAO")
public class P0003DAOImpl implements P0003DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<P0003VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<P0003VO> list = sqlSession.selectList("hm.p0003.searchList", searchMap);
		return list;
	}
	
	@Override
	public List<P0003VO> searchMod(Map<String, Object> searchMap) throws DataAccessException{
		List<P0003VO> list = sqlSession.selectList("hm.p0003.searchMod", searchMap);
		return list;
	}
	
	@Override
	public void updateMember(Map<String, Object> datahMap) throws DataAccessException{
		sqlSession.update("hm.p0003.updateMember", datahMap);
	}
}