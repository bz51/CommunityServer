package com.resident.action;

import com.core.util.URLEcoder;
import com.opensymphony.xwork2.ActionSupport;
import com.resident.entity.ResidentEntity;
import com.resident.service.UserService;

public class UserAction extends ActionSupport {
	//客户端与服务器传递的参数
	private ResidentEntity residentEntity;
	private String result;
	private String error;
	private String community_id;
	//全局Service
	private UserService service = new UserService();
	
	/**
	 * 根据户主id完善该住户的信息
	 * @return
	 */
	public String setUser(){
		if(residentEntity==null || residentEntity.getId()==0 || residentEntity.getCommunity_id()==0){
			error = "参数不能为空！";
			return ERROR;
		}
		
		//对姓名进行解码
		residentEntity.setName(URLEcoder.decode(residentEntity.getName()));
		
		//将住户信息存入数据库
		if(service.setUser(residentEntity))
			community_id = residentEntity.getCommunity_id()+"";
		else{
			error = "服务器端发生异常，完善信息失败！请重试";
			return ERROR;
		}
		return "success_setUser";
	}

	
	/**
	 * 根据id获取住户信息
	 * @return
	 */
	public String getUser(){
		if(residentEntity==null || residentEntity.getId()==0){
			error = "参数不能为空！";
			return ERROR;
		}
		
		residentEntity = service.getUser(residentEntity);
		if(residentEntity==null){
			error = "服务器发生异常，获取户主信息失败！请重试";
			return ERROR;
		}
		return "success_getUser";
	}
	
	public ResidentEntity getResidentEntity() {
		return residentEntity;
	}

	public void setResidentEntity(ResidentEntity residentEntity) {
		this.residentEntity = residentEntity;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}


	public String getCommunity_id() {
		return community_id;
	}


	public void setCommunity_id(String community_id) {
		this.community_id = community_id;
	}
	
	
}
