package com.manager.service;

import com.manager.dao.LoginDao;
import com.manager.entity.ManagerEntity;

public class LoginService {
	private LoginDao dao = new LoginDao();
	
	/**
	 * 登陆
	 * @param managerEntity
	 * @return
	 */
	public ManagerEntity login(ManagerEntity managerEntity) {
		return dao.login(managerEntity);
	}

	/**
	 * 将注册信息存入数据库
	 * @param managerEntity
	 * @return
	 */
	public long signin(ManagerEntity managerEntity) {
		return dao.signin(managerEntity);
	}

	
	/**
	 * 判断该用户名是否已被注册
	 * @param managerEntity
	 * @return
	 */
	public boolean isLogin(ManagerEntity managerEntity) {
		long is_login = dao.isLogin(managerEntity.getUsername());
		if(is_login!=0)
			return false;
		return true;
	}

}
