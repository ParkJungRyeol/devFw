package project.hm.s0003.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import project.hm.s0003.dao.S0003DAO;
import project.hm.s0003.vo.S0003VO;



@Service("s0003Service")
@Transactional(propagation = Propagation.REQUIRED)
public class S0003ServiceImpl implements S0003Service {
	@Autowired
	private S0003DAO s0003DAO;

	@Override
	public List<S0003VO> searchList(Map<String, Object> searchMap) throws DataAccessException {
		List<S0003VO> list =  s0003DAO.searchList(searchMap); 
		return list;
	}

	@Override
	public void saveData(Map<String, String[]> dataMap)  throws DataAccessException  {
		String[] status = dataMap.get("STATUS");
		int length = status.length; // row수
		int i = 0;
		
		for(String str : status) {
			Map<String, String> row = getRow(dataMap, length, i); // 현재 Index의 Row Map
			if("I".equals(str)) { // 추가
				s0003DAO.insertData(row);
			}else if("U".equals(str)) { // 수정
				s0003DAO.updateData(row);
			}else if("D".equals(str)) { // 삭제
				s0003DAO.deleteData(row);
			}
			i++;
		}
	}
	
	private Map getRow(Map<String, String[]> dataMap, int length, int index) {
		Map<String, String> row = new HashMap<String, String>();
		for(String name : dataMap.keySet()) {
			String[] data = dataMap.get(name);
			if(length == data.length) {
				row.put(name, data[index]);
			}
		}		
		return row;
	}	
}
