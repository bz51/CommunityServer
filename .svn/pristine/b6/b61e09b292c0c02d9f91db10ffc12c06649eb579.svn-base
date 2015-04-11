package com.resident.service;

import com.resident.dao.LoginDao;
import com.resident.entity.ResidentEntity;

public class LoginService {
	private LoginDao dao = new LoginDao();
	
	/**
	 * 检查该用户名是否已被注册
	 */
	public boolean isLogin(String username) {
		long result = dao.isLogin(username);
		return result==0?true:false;
	}

	/**
	 * 注册
	 * @param residentEntity
	 * @param is_login 
	 * @return
	 */
	public long signin(ResidentEntity residentEntity, String is_login) {
		//若还未检测用户名是否可用，先要检测
		if(is_login==null || is_login.equals("") || is_login.equals("no")){
			boolean result = this.isLogin(residentEntity.getUsername());
			if(!result)
				return -1;
		}
		
		return dao.signin(residentEntity);
	}
	

	
	/**
	 * 登陆
	 * @param residentEntity
	 * @return
	 */
	public ResidentEntity login(ResidentEntity residentEntity) {
		return dao.login(residentEntity);
	}

}
