package project.hm.p0004.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import project.hm.p0004.vo.P0004VO;

@Repository("p0004DAO")
public class P0004DAOImpl implements P0004DAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<P0004VO> searchMember(Map<String, Object> searchMap) throws DataAccessException{
		List<P0004VO> list = sqlSession.selectList("hm.p0004.searchMember", searchMap);
		return list;
	}
	
	@Override
	public void updateMember(Map<String, Object> datahMap) throws DataAccessException{
		sqlSession.update("hm.p0004.updateMember", datahMap);
	}
}