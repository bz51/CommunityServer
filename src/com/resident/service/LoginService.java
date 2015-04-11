package com.resident.service;

import com.resident.dao.LoginDao;
import com.resident.entity.ResidentEntity;

public class LoginService {
	private LoginDao dao = new LoginDao();
	
	/**
	 * �����û����Ƿ��ѱ�ע��
	 */
	public boolean isLogin(String username) {
		long result = dao.isLogin(username);
		return result==0?true:false;
	}

	/**
	 * ע��
	 * @param residentEntity
	 * @param is_login 
	 * @return
	 */
	public long signin(ResidentEntity residentEntity, String is_login) {
		//����δ����û����Ƿ���ã���Ҫ���
		if(is_login==null || is_login.equals("") || is_login.equals("no")){
			boolean result = this.isLogin(residentEntity.getUsername());
			if(!result)
				return -1;
		}
		
		return dao.signin(residentEntity);
	}
	

	
	/**
	 * ��½
	 * @param residentEntity
	 * @return
	 */
	public ResidentEntity login(ResidentEntity residentEntity) {
		return dao.login(residentEntity);
	}

}
