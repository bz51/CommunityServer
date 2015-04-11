package com.resident.service;

import java.util.List;

import com.core.util.Pagination;
import com.manager.entity.CommunityEntity;
import com.resident.dao.CommunityDao;
import com.resident.dao.UserDao;
import com.resident.entity.InfoEntity;
import com.resident.entity.PhoneEntity;
import com.resident.entity.ResidentEntity;

public class CommunityService {
	private CommunityDao dao = new CommunityDao();
	
	/**
	 * 根据小区名字搜索相关的小区
	 * @param communityEntity
	 * @return
	 */
	public Pagination getCommunity(CommunityEntity communityEntity,Pagination pagination) {
		return dao.getCommunity(communityEntity,pagination);
	}

	/**
	 * 获取推送给“我的”公告
	 * @param residentEntity
	 * @return
	 */
	public List<InfoEntity> getMyInfo(ResidentEntity residentEntity) {
		//获取“我的”个人信息
		residentEntity = new UserDao().getUser(residentEntity.getId());
		
		
		//根据我的信息获取我的公告
		if(residentEntity==null)
			return null;
		
		//将community_id、building_id、area_id、floor_id、house_id为0的值设成-1
		if(residentEntity.getCommunity_id()==0)
			residentEntity.setCommunity_id(-1);
		if(residentEntity.getBuild_id()==0)
			residentEntity.setBuild_id(-1);
		if(residentEntity.getArea_id()==0)
			residentEntity.setArea_id(-1);
		if(residentEntity.getFloor_id()==0)
			residentEntity.setFloor_id(-1);
		if(residentEntity.getHouse_id()==0)
			residentEntity.setHouse_id(-1);
		
		List<InfoEntity> list = dao.getMyInfo(residentEntity);
		
		System.out.println("list_size:"+list.size());
		
		if(list.size()==0)
			return null;
		else
			return list;
	}

	/**
	 * 获取推送给“我的”公告的数量
	 * @param residentEntity
	 * @return
	 */
	public int getMyInfoCount(ResidentEntity residentEntity) {
		//获取“我的”个人信息
		residentEntity = new UserDao().getUser(residentEntity.getId());
		
		//根据我的信息获取我的公告
		if(residentEntity==null)
			return -1;
		
		//将community_id、building_id、area_id、floor_id、house_id为0的值设成-1
		if(residentEntity.getCommunity_id()==0)
			residentEntity.setCommunity_id(-1);
		if(residentEntity.getBuild_id()==0)
			residentEntity.setBuild_id(-1);
		if(residentEntity.getArea_id()==0)
			residentEntity.setArea_id(-1);
		if(residentEntity.getFloor_id()==0)
			residentEntity.setFloor_id(-1);
		if(residentEntity.getHouse_id()==0)
			residentEntity.setHouse_id(-1);
		
		List<InfoEntity> list = dao.getMyInfo(residentEntity);
		
		return list.size();
	}

	/**
	 * 获取本小区便民电话
	 * @param community_id
	 * @return
	 */
	public List<PhoneEntity> getPhone(long community_id) {
		return dao.getPhone(community_id);
	}

}
