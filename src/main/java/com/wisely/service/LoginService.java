package com.wisely.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisely.dao.LoginDao;
import com.wisely.entity.User;
import com.wisely.rest.LoginRest;


@Service
public class LoginService {
	@Autowired
	LoginDao loginDao;
	private static final Logger log = LoggerFactory.getLogger(LoginRest.class);

	public void login(String account, String password) throws Exception {

		/*
		 * 查询用户是否存在
		 */
		User user = loginDao.findByAccountAndDelFlag(account, true);
		if(user == null) {
			throw new Exception("用户不存在，请重新输入！");
		}
		
		/*
		 * 判断用户密码是否正确
		 */
		if(!password.equals(user.getPassword())) {
			throw new Exception("密码错误，请重新输入！");
		}
		
		log.info("用户登录成功");
	}
	
	public User findByAccountAndDelFlag(String account, boolean delFlag) {
		return loginDao.findByAccountAndDelFlag(account, true);
	}
	
	public User findByPasswordAndDelFlag(String password, boolean delFlag) {
		return loginDao.findByPasswordAndDelFlag(password, true);
	}
	
	public User saveUser(User user) {
		try {
			/*
			 * 入参检测
			 */
				if(user == null) {
					return user;
				}
			
			/*
			 * 保存数据
			 */
				user = loginDao.save(user);
		}catch(Exception e) {
			log.error("saveUser");
			e.printStackTrace();
			return null;
		}
		return user;
	}

}
