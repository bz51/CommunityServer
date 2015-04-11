package com.resident.action;

import java.util.List;

import com.core.util.Pagination;
import com.core.util.URLEcoder;
import com.manager.entity.CommunityEntity;
import com.opensymphony.xwork2.ActionSupport;
import com.resident.entity.InfoEntity;
import com.resident.entity.PhoneEntity;
import com.resident.entity.ResidentEntity;
import com.resident.service.CommunityService;

public class CommunityAction extends ActionSupport {
	// 全局Service
	private CommunityService service = new CommunityService();
	// 客户端与服务器传递的参数
	private List<CommunityEntity> communityList;
	private List<InfoEntity> infoList;
	private CommunityEntity communityEntity;
	private ResidentEntity residentEntity;
	private String error;
	private Pagination pagination;
	private String result;
	private PhoneEntity phoneEntity;
	private List<PhoneEntity> phoneList;

	/**
	 * 根据小区名字搜索相关的小区
	 * 
	 * @return
	 */
	public String getCommunity() {
		if (communityEntity == null || communityEntity.getName() == null
				) {
			error = "传入参数不能为空！";
			return ERROR;
		}
		
		//对关键字解码
		communityEntity.setName(URLEcoder.decode(communityEntity.getName()));

		// 根据小区名字搜索相关的小区
		pagination = service.getCommunity(communityEntity,pagination);
		return "success_getCommunity";
	}


	/**
	 * 获取推送给“我的”公告
	 * @return
	 */
	public String getMyInfo(){
		if(residentEntity==null || residentEntity.getId()==0){
			error = "传入参数不能为空！";
			return ERROR;
		}
		
		//获取推送给“我的”公告
		infoList = service.getMyInfo(residentEntity);
		return "success_getMyInfo";
	}
	
	/**
	 * 获取推送给“我的”公告的数量
	 * @return
	 */
	public String getMyInfoCount(){
		if(residentEntity==null || residentEntity.getId()==0){
			error = "传入参数不能为空！";
			return ERROR;
		}
		
		//获取推送给“我的”公告的数量
		int size = service.getMyInfoCount(residentEntity);
		if(size==-1)
			return ERROR;
		result = size+"";
		return "success_getMyInfoCount";
	}
	
	/**
	 * 获取所有便民电话
	 * @return
	 */
	public String getPhone(){
		if(phoneEntity==null || phoneEntity.getCommunity_id()==0){
			error = "传入参数不能为空！";
			return ERROR;
		}
		
		phoneList = service.getPhone(phoneEntity.getCommunity_id());
		return "success_getPhone";
	}
	
	public List<CommunityEntity> getCommunityList() {
		return communityList;
	}


	public void setCommunityList(List<CommunityEntity> communityList) {
		this.communityList = communityList;
	}


	public CommunityEntity getCommunityEntity() {
		return communityEntity;
	}

	public void setCommunityEntity(CommunityEntity communityEntity) {
		this.communityEntity = communityEntity;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}


	public List<InfoEntity> getInfoList() {
		return infoList;
	}


	public void setInfoList(List<InfoEntity> infoList) {
		this.infoList = infoList;
	}


	public ResidentEntity getResidentEntity() {
		return residentEntity;
	}


	public void setResidentEntity(ResidentEntity residentEntity) {
		this.residentEntity = residentEntity;
	}


	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public PhoneEntity getPhoneEntity() {
		return phoneEntity;
	}


	public void setPhoneEntity(PhoneEntity phoneEntity) {
		this.phoneEntity = phoneEntity;
	}


	public List<PhoneEntity> getPhoneList() {
		return phoneList;
	}


	public void setPhoneList(List<PhoneEntity> phoneList) {
		this.phoneList = phoneList;
	}

}
