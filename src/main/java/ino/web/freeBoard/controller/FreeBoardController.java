package ino.web.freeBoard.controller;

import ino.web.commonCode.service.CommCodeService;
import ino.web.freeBoard.dto.FreeBoardDto;
import ino.web.freeBoard.service.FreeBoardService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;

@Controller
public class FreeBoardController {
	
	@Autowired
	private FreeBoardService freeBoardService;
	@Autowired
	private CommCodeService commCodeService;
	
	
	@RequestMapping("/main.ino")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		List<HashMap<String, Object>> list = freeBoardService.freeBoardList();
		String json=new Gson().toJson(list);
		
		List<HashMap<String,Object>> list2 = commCodeService.selectCommonCodeList2();
		System.out.println("공통 코드 : "+list2);
		
		mav.setViewName("boardMain");
		mav.addObject("freeBoardList",json);
		mav.addObject("list2",list2);
		return mav;
	}
	
	@RequestMapping("/freeBoardInsert.ino")
	public String freeBoardInsert(){
		return "freeBoardInsert";
	}
	
	@RequestMapping("/freeBoardInsertPro.ino")
	@ResponseBody
	public int freeBoardInsertPro(HttpServletRequest request, @RequestParam("name") String name,
																 @RequestParam("title") String title,
																 @RequestParam("content") String content){
		HashMap<String, Object> map= new HashMap<>();
		map.put("name", name);
		map.put("title", title);
		map.put("content", content);
		System.out.println("HI");
		try{
			int result=freeBoardService.freeBoardInsertPro(map);
			System.out.println("글쓰기 result="+result);
			if(result!=0){
				int num=freeBoardService.getNewNum();
				System.out.println("num="+num);
				return num;
			}	
			else
				return 0;
		}catch (Exception e) {
			return 0;
		}
		
	}
	
	@RequestMapping("/freeBoardDetail.ino")
	public ModelAndView freeBoardDetail(HttpServletRequest request, int num){
		HashMap<String, Object> map=freeBoardService.getDetailByNum(num);
		return new ModelAndView("freeBoardDetail", "freeBoardMap", map);
	}
	
	@RequestMapping("/freeBoardModify.ino")
	@ResponseBody
	public String freeBoardModify(HttpServletRequest request, @RequestParam("name") String name,
															  @RequestParam("num") String num,
			 											      @RequestParam("title") String title,
			 											      @RequestParam("regdate") String regdate,
			 												  @RequestParam("content") String content){
		HashMap<String, Object> map= new HashMap<>();
		map.put("name", name);
		map.put("num", num);
		map.put("title", title);
		map.put("regdate", regdate);
		map.put("content", content);
		try {
			int result=freeBoardService.freeBoardModify(map);
			if(result!=0){
				return "Success";
			}
			else{
				return "fail";
			}
			
		} catch (Exception e) {
			return "fail";
		}
	}
	
		
	@RequestMapping("/freeBoardDelete.ino")
	@ResponseBody
	public String FreeBoardDelete(int num){
		int result=freeBoardService.FreeBoardDelete(num);
		System.out.println("result="+result);
		try {
			if(result!=0){
				return "Success";
			}
			else{
				return "fail";
			}
			
		} catch (Exception e) {
			return "fail";
		}
	}
	
	
	@RequestMapping(value ="/freeBoardSearch.ino")
	@ResponseBody
	public List<HashMap<String, Object>> FreeBoardSearch(@RequestParam("sel") String sel, @RequestParam("search") String search){
		System.out.println(sel+"\t"+search);
		HashMap<String, Object> map= new HashMap<>();
		map.put("sel", sel);
		map.put("search", search);
		try {
			List<HashMap<String, Object>> list =freeBoardService.freeBoardList(map);
			System.out.println("searchList="+list);
			return list;
			
		} catch (Exception e) {
			return null;
		}
		
	}
}