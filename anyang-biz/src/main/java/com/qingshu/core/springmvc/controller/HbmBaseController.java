package com.qingshu.core.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qingshu.core.hibernate.service.HbmCommonService;

/**
 * 
 * 类描述：hibernate基础控制器
 */
@Controller
public class HbmBaseController {
	@Autowired
	protected HbmCommonService hbmCommonService;

}
