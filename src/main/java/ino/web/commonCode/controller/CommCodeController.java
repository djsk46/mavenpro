package ino.web.commonCode.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ino.web.commonCode.service.CommCodeService;

@Controller
public class CommCodeController {
	
	@Autowired 
	private CommCodeService commCodeService;
	
	@RequestMapping("/commonCode.ino")
	public ModelAndView commonCode(HttpServletRequest req){
		
		ModelAndView mav = new ModelAndView();
		
		List<HashMap<String,Object>> list = commCodeService.selectCommonCodeList();
		
		mav.addObject("list" , list);
		mav.setViewName("commonCodeMain");
		
		return mav;
	}
	
//	@RequestMapping("/commonCode2.ino")
//	public ModelAndView commonCode2(HttpServletRequest req){
//		
//		ModelAndView mav = new ModelAndView();
//		System.out.println("공통코드컨트롤러");
//		List<HashMap<String,Object>> list = commCodeService.selectCommonCodeList2();
//		System.out.println("공통 코드 : "+list);
//		mav.addObject("list" , list);
//		mav.setViewName("freeBoard/freeBoardMain");
//		
//		return mav;
//	}
	
//	@RequestMapping("/commonCode2.ino")
//	@ResponseBody
//	public List<HashMap<String,Object>> commonCode2(HttpServletRequest req){
//		String code="COM001";
//		System.out.println("공통코드컨트롤러");
//		List<HashMap<String,Object>> list = commCodeService.selectCommonCodeList2(code);
//		System.out.println("공통 코드 : "+list);
//		return list;
//	}
	
}
