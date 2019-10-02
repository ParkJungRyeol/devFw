package project.hm.s0003.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import project.hm.s0003.vo.S0003VO;

public interface S0003DAO {
	 public List<S0003VO> searchList(Map<String, Object> searchMap) throws DataAccessException;

		public void insertData(Map<String, String> row) throws DataAccessException;

		public void updateData(Map<String, String> row) throws DataAccessException;

		public void deleteData(Map<String, String> row) throws DataAccessException;
}