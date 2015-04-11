package com.manager.action;

import com.core.util.Pagination;
import com.core.util.URLEcoder;
import com.manager.service.CommunityService;
import com.opensymphony.xwork2.ActionSupport;
import com.resident.entity.InfoEntity;
import com.resident.entity.PhoneEntity;
import com.resident.entity.ResidentEntity;

public class CommunityAction extends ActionSupport {
	//客户端与服务器传递的参数
	private String result;
	private String error;
	private InfoEntity infoEntity;
	private Pagination pagination;
	private ResidentEntity residentEntity;
	private PhoneEntity phoneEntity;
	//全局Service
	private CommunityService service = new CommunityService();
	
	/**
	 * 发布小区公告
	 */
	public String postInfo(){
		if(infoEntity==null
				 || infoEntity.getContent().equals("")){
//			if(infoEntity==null || infoEntity.getArea_id()==0
//					|| infoEntity.getContent().equals("")
//					|| infoEntity.getPost_id()==0 || infoEntity.getCommunity_id()==0){
			error = "传递的参数不能为空！";
			return ERROR;
		}
		
		//转码
		infoEntity.setContent(URLEcoder.decode(infoEntity.getContent()));
		
		//发布公告
		boolean is_post = service.postInfo(infoEntity);
		if(is_post)
			result = "yes";
		else
			result = "no";
		return "success_postInfo";
	}
	
	
	/**
	 * 获取本小区所有公告
	 * @return
	 */
	public String getInfos(){
		if(infoEntity==null || infoEntity.getCommunity_id()==0){
			error = "传递的参数不能为空！";
			return ERROR;
		}
		
		//获取本小区所有公告
		pagination = service.getInfos(infoEntity,pagination);
		return "success_getInfos";
	}
	
	/**
	 * 根据相关信息搜索户主信息
	 * @return
	 */
	public String searchUser(){
		if(residentEntity==null || residentEntity.getCommunity_id()==0){
			error = "传递的参数不能为空！";
			return ERROR;
		}
		
		//搜索
		pagination = service.searchUser(residentEntity,pagination);
		return "success_searchUser";
	}
	
	/**
	 * 发布一个便民电话
	 * @return
	 */
	public String postPhone(){
		if(phoneEntity==null || phoneEntity.getCommunity_id()==0 || phoneEntity.getPhone()==null || phoneEntity.getText()==null){
			error = "传递的参数不能为空！";
			return ERROR;
		}
		
		//转码
		phoneEntity.setText(URLEcoder.decode(phoneEntity.getText()));
		
		boolean isSuccess = service.postPhone(phoneEntity);
		if(isSuccess)
			result = "yes";
		else
			result = "no";
		return "success_postPhone";
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public InfoEntity getInfoEntity() {
		return infoEntity;
	}

	public void setInfoEntity(InfoEntity infoEntity) {
		this.infoEntity = infoEntity;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}


	public Pagination getPagination() {
		return pagination;
	}


	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public ResidentEntity getResidentEntity() {
		return residentEntity;
	}


	public void setResidentEntity(ResidentEntity residentEntity) {
		this.residentEntity = residentEntity;
	}


	public PhoneEntity getPhoneEntity() {
		return phoneEntity;
	}


	public void setPhoneEntity(PhoneEntity phoneEntity) {
		this.phoneEntity = phoneEntity;
	}
	
	
}
