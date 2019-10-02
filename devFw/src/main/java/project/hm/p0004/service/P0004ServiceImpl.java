package project.hm.p0004.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.hm.p0004.dao.P0004DAO;
import project.hm.p0004.vo.P0004VO;

@Service("p0004Service")
@Transactional(propagation = Propagation.REQUIRED)
public class P0004ServiceImpl implements P0004Service{
	@Autowired
	private P0004DAO p0004DAO;
	
	@Override
	public List<P0004VO> searchMember(Map<String, Object> searchMap) throws DataAccessException{
		List<P0004VO> list = p0004DAO.searchMember(searchMap);
		return list;
	}
	
	@Override
	public void updateMember(Map<String, Object> datahMap) throws Exception{
		p0004DAO.updateMember(datahMap);
	}
}