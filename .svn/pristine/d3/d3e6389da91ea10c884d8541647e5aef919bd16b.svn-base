package com.manager.service;

import com.core.util.Pagination;
import com.manager.dao.CommunityDao;
import com.resident.entity.InfoEntity;
import com.resident.entity.PhoneEntity;
import com.resident.entity.ResidentEntity;

public class CommunityService {
	private CommunityDao dao = new CommunityDao();
	
	/**
	 * 发布公告
	 * @param infoEntity
	 * @return
	 */
	public boolean postInfo(InfoEntity infoEntity) {
		return dao.postInfo(infoEntity);
	}

	
	/**
	 * 获取本小区所有公告
	 * @param infoEntity
	 * @param pagination 
	 * @return
	 */
	public Pagination getInfos(InfoEntity infoEntity, Pagination pagination) {
		return dao.getInfos(infoEntity.getCommunity_id(),pagination);
	}


	
	/**
	 * 搜索住户
	 * @param residentEntity
	 * @param pagination 
	 * @return
	 */
	public Pagination searchUser(ResidentEntity residentEntity, Pagination pagination) {
		return dao.searchUser(residentEntity,pagination);
	}


	/**
	 * 发布一个便民电话
	 * @param phoneEntity
	 * @return
	 */
	public boolean postPhone(PhoneEntity phoneEntity) {
		return dao.postPhone(phoneEntity);
	}

}
