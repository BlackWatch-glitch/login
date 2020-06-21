package com.wisely.rest;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.wisely.service.LoginService;


@RestController
@RequestMapping("/web/rest/user")
public class LoginRest {

	private static final Logger log = LoggerFactory.getLogger(LoginRest.class);
	
	@Autowired
	private LoginService loginSerivce;
	
	@RequestMapping("/login")
	public JSONObject login(@RequestBody String requestStr, HttpServletRequest httpServletRequest) {
		log.info("login:" + requestStr);
		JSONObject response = new JSONObject();
		try {
			JSONObject request = JSON.parseObject(requestStr);
			/*
			 *1、 获取前端数据
			 */
			String account = request.getString("account");
			String password = request.getString("password");
			
			/*
			 * 2、验证前端数据
			 */
			if(account == null || account.isEmpty()) {
				throw new Exception("缺少user字段！");
			}
			if(password == null || password.isEmpty()) {
				throw new Exception("缺少password字段！");
			}
			
			/*
			 * 3、登录
			 */
			loginSerivce.login(account, password);
			/*
			 * 4、返回数据
			 */
			response.put("res", true);
		}catch(Exception e) {
			log.error("login" + e.getMessage());
			response.put("res", false);
			response.put("exception", e.getMessage());
		}
		return response;
	}
	
}
