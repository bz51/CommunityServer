package com.resident.service;

import java.util.List;

import com.manager.entity.CommunityEntity;
import com.resident.dao.UserDao;
import com.resident.entity.ResidentEntity;

public class UserService {
	private UserDao dao = new UserDao();

	/**
	 * 将住户信息存入数据库
	 * @param residentEntity
	 */
	public boolean setUser(ResidentEntity residentEntity) {
		int result = dao.setUser(residentEntity);
		return result==1?true:false;
	}

	/**
	 * 根据住户id获取住户信息
	 * @param residentEntity
	 * @return
	 */
	public ResidentEntity getUser(ResidentEntity residentEntity) {
		return dao.getUser(residentEntity.getId());
	}
	
	

}
