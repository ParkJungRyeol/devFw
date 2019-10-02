package project.hm.p0003.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import project.hm.p0003.service.P0003Service;
import project.hm.p0003.vo.P0003VO;

@Controller("p0003Controller")
public class P0003ControllerImpl implements P0003Controller{
	private static final Logger logger = LoggerFactory.getLogger(P0003ControllerImpl.class);
	@Autowired
	P0003Service p0003Service;
	@Autowired
	P0003VO p0003VO;
	
	@Override
	@RequestMapping(value = "/hm/p0003/searchInit.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView searchInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("hm/p0003_init");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/hm/p0003/searchList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView searchList(@RequestParam(value="cust_id", required=false) String cust_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("cust_id", cust_id);
		
		List list = p0003Service.searchList(searchMap);
		
		ModelAndView mav = new ModelAndView("hm/p0003_search");
		mav.addObject("searchList", list);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/hm/p0003/searchMod.do", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView searchMod(@RequestParam(value="p_mod_id", required=false) String cust_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("cust_id", cust_id);
		
		List list = p0003Service.searchMod(searchMap);
		if(!list.isEmpty()) {
			p0003VO = (P0003VO)list.get(0);
		}
		
		ModelAndView mav = new ModelAndView("hm/p0003_mod");
		mav.addObject("p0003VO", p0003VO);
		mav.addObject("command", "modSearch");
		return mav;
	}

	@Override
	@RequestMapping(value = "/hm/p0003/updateMember.do", method = { RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity updateMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Enumeration enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = request.getParameter(name);
			dataMap.put(name, value);
		} //파라미터 정보를 Map 객체에 추가
		
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			p0003Service.updateMember(dataMap);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/hm/p0003/searchList.do");
			dispatch.forward(request, response);
		}catch(Exception e) {
			message = "<script>";
			message += " alert('오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/hm/p0003/searchInit.do'; ";
			message += " </script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}
}