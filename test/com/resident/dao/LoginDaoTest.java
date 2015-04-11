package com.resident.dao;

import org.junit.Test;

import com.resident.entity.ResidentEntity;

public class LoginDaoTest {
	private LoginDao dao = new LoginDao();
	
	//@Test
	public void isLogin() {
		System.out.println("result:"+dao.isLogin("chai"));
	}

//	@Test
	public void signin(){
		ResidentEntity resident = new ResidentEntity();
		resident.setUsername("bz51");
		resident.setPassword("12345678");
		dao.signin(resident);
	}
	
	@Test
	public void login(){
		ResidentEntity resident = new ResidentEntity();
		resident.setUsername("bz511");
		resident.setPassword("12345678");
		System.out.println("result:"+dao.login(resident));
	}
}
