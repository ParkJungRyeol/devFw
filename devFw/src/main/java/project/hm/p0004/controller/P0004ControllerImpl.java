package project.hm.p0004.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import oracle.net.aso.e;
import project.hm.p0004.service.P0004Service;
import project.hm.p0004.vo.P0004VO;



@Controller("p0004Controller")
public class P0004ControllerImpl implements P0004Controller {
	private static final Logger logger = LoggerFactory.getLogger(P0004ControllerImpl.class);
	@Autowired
	P0004Service p0004Service;
	@Autowired
	P0004VO p0004VO;
	
	@Override
	@RequestMapping(value = "/hm/p0004/searchInit.do", method = { RequestMethod.GET, RequestMethod.POST})
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("hm/p0004_ajax");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/hm/p0004/searchMember.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> searchMember(@RequestParam(value="p_cust_id", required=false) String p_cust_id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		searchMap.put("p_cust_id", p_cust_id);
		
		List list = null;
		try {
			list = p0004Service.searchMember(searchMap);
			if(!list.isEmpty()) {
				p0004VO = (P0004VO)list.get(0);
				resultMap = BeanUtils.describe(p0004VO);
				resultMap.put("error_yn", "N");
			}else{
				resultMap.put("error_yn", "Y");
				resultMap.put("error_text", "존재하지 않습니다.");
			}
		}catch(Exception e) {
			resultMap.put("error_yn", "Y");
			resultMap.put("error_text", "에러발생");
			e.printStackTrace();
		}
		
		System.out.println("==========================>>"+resultMap.toString());
		return resultMap;
	}

	@Override
	@RequestMapping(value = "/hm/p0004/updateMember.do", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		}
		
		System.out.println("=======================>>"+dataMap.toString());
		
		try {
			p0004Service.updateMember(dataMap);
			resultMap.put("error_yn", "N");	
		} catch (Exception e) {
			resultMap.put("error_yn", "Y");
			resultMap.put("error_text", "에러발생");
			e.printStackTrace();
		}		
		return resultMap;
	}
}