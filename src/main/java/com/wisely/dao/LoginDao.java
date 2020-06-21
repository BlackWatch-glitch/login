package com.wisely.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wisely.entity.User;



@Repository
public interface LoginDao extends JpaRepository<User, Integer> {
	User findByAccountAndDelFlag(String account, boolean delFlag);

	User findByPasswordAndDelFlag(String password, boolean delFlag);
	
	
}
