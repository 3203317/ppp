package com.qingshu.base.mybatis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qingshu.common.json.model.Calendar;

/**
 * 
 * 类描述：系统基础模块控制器
 */
@Controller
public class SystemController {
	
	/**
	 * 我的日程页面
	 * 
	 * @param request
	 */
	@RequestMapping("/calendar.do")
	public String left(HttpServletRequest request, ModelMap modelMap) {
		return "blue/calendar/fullCalendar";
	}
	/**
	 * 我的日程页面
	 * 
	 * @param request
	 */
	@RequestMapping("/mycalendar.do")
	@ResponseBody
	public List<Calendar> mycalendar(HttpServletRequest request, ModelMap modelMap) {
		//String userId=request.getParameter("viewname");
		//String start=request.getParameter("end");
		List<Calendar> calendars=new ArrayList<Calendar>();
		Calendar calendar=new Calendar();
		calendar.setStart("2013-06-06");
		calendar.setTitle("我的日程安排<br>sdfsdf");
		calendars.add(calendar);
		return calendars;
	}
}
