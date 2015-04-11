package com.manager.action;

import com.manager.entity.ManagerEntity;
import com.manager.service.LoginService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	//�ͻ�������������ݵĲ���
	private ManagerEntity managerEntity;
	private String user_id;
	private String community_id;
	private String community_name;
	private String is_super;
	private String username;
	private String error;
	private String result;
	//ȫ��Service
	private LoginService service = new LoginService();
	
	/**
	 * ��½
	 */
	public String login(){
		if(managerEntity==null || managerEntity.getUsername()==null || managerEntity.getPassword()==null){
			error = "���ݵĲ�������Ϊ��!";
			return ERROR;
		}
		
		//��½
		managerEntity = service.login(managerEntity);
		
		if(managerEntity==null){
			error = "��½ʧ�ܣ�������";
			return ERROR;
		}
		
		user_id = managerEntity.getId()+"";
		community_id = managerEntity.getCommunity_id()+"";
		community_name = managerEntity.getCommunity_name();
		username = managerEntity.getUsername();
		is_super = managerEntity.getIs_super()+"";
		return "success_login";
	}

	
	/**
	 * ע��
	 * @return
	 */
	public String signin(){
		if(managerEntity==null || managerEntity.getUsername()==null ||
				managerEntity.getPassword()==null || managerEntity.getCommunity_id()==0){
			error = "���ݵĲ�������Ϊ�գ�";
			return ERROR;
		}
		
		//��ע����Ϣ�������ݿ�
		long id = service.signin(managerEntity);
		
		if(id==-1){
			error = "ע��ʧ�ܣ�������";
			return ERROR;
		}
		
		user_id = id+"";
		community_id = managerEntity.getCommunity_id()+"";
		is_super = "1";
		username = managerEntity.getUsername();
		return "success_signin";
	}
	
	
	/**
	 * �жϸ��û����Ƿ��ѱ�ע��
	 * @return
	 */
	public String isLogin(){
		if(managerEntity==null || managerEntity.getUsername()==null){
			error = "���ݵĲ�������Ϊ�գ�";
			return ERROR;
		}
		
		//�жϸ��û����Ƿ��ѱ�ע��
		boolean is_login = service.isLogin(managerEntity);
		if(is_login)
			result = "yes";
		else
			result = "no";
		return "success_isLogin";
	}
	
	public ManagerEntity getManagerEntity() {
		return managerEntity;
	}

	public void setManagerEntity(ManagerEntity managerEntity) {
		this.managerEntity = managerEntity;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCommunity_id() {
		return community_id;
	}

	public void setCommunity_id(String community_id) {
		this.community_id = community_id;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getIs_super() {
		return is_super;
	}

	public void setIs_super(String is_super) {
		this.is_super = is_super;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
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
