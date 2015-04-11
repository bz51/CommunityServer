package com.resident.action;

import com.opensymphony.xwork2.ActionSupport;
import com.resident.entity.ResidentEntity;
import com.resident.service.LoginService;

public class LoginAction extends ActionSupport {
	//客户端与服务器传递的实体
	private ResidentEntity residentEntity;
	private String result;
	private String error;
	private String community_id;
	private String user_id;
	private String username;
	private String is_login;
	private String community_name;
	//本类全局Service
	private LoginService service = new LoginService();
	
	
	/**
	 * 判断用户名是否已被注册
	 */
	public String isLogin(){
		if(residentEntity==null || residentEntity.getUsername()==null ||residentEntity.getUsername().equals("")){
			error = "参数residentEntity.username不能为空！";
			return ERROR;
		}
		
		//检查username是否已经被注册
		boolean isLogin = service.isLogin(residentEntity.getUsername());
		
		//返回检查结果
		if(isLogin)
			result = "yes";
		else
			result = "no";
		
		return "success_isLogin";
	}
	
	/**
	 * 注册
	 */
	public String signin(){
		if(residentEntity==null 
				|| residentEntity.getUsername()==null || residentEntity.getPassword()==null
				|| residentEntity.getUsername().equals("") || residentEntity.getUsername().equals("")){
			error = "参数不能为空！";
			return ERROR;
		}
		
		//将用户存入数据库
		long id = service.signin(residentEntity,is_login);
		if(id==-1){
			error = "服务器端发生异常，注册失败！请重试";
			return ERROR;
		}
		user_id = id+"";
		username = residentEntity.getUsername();
		return "success_signin";
	}

	
	/**
	 * 登陆
	 */
	public String login(){
		if(residentEntity==null 
				|| residentEntity.getUsername()==null || residentEntity.getPassword()==null
				|| residentEntity.getUsername().equals("") || residentEntity.getPassword().equals("")){
			error = "参数不能为空！";
			return ERROR;
		}
		
		residentEntity = service.login(residentEntity);
		if(residentEntity==null){
			error = "登陆失败！";
			return ERROR;
		}
		user_id = residentEntity.getId()+"";
		community_id = residentEntity.getCommunity_id()+"";
		username = residentEntity.getUsername();
		community_name = residentEntity.getCommunity_name();
		return "success_login";
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

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getIs_login() {
		return is_login;
	}

	public void setIs_login(String is_login) {
		this.is_login = is_login;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCommunity_name() {
		return community_name;
	}

	public void setCommunity_name(String community_name) {
		this.community_name = community_name;
	}


}
