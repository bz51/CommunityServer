package com.resident.action;

import com.core.util.URLEcoder;
import com.opensymphony.xwork2.ActionSupport;
import com.resident.entity.ResidentEntity;
import com.resident.service.UserService;

public class UserAction extends ActionSupport {
	//�ͻ�������������ݵĲ���
	private ResidentEntity residentEntity;
	private String result;
	private String error;
	private String community_id;
	//ȫ��Service
	private UserService service = new UserService();
	
	/**
	 * ���ݻ���id���Ƹ�ס������Ϣ
	 * @return
	 */
	public String setUser(){
		if(residentEntity==null || residentEntity.getId()==0 || residentEntity.getCommunity_id()==0){
			error = "��������Ϊ�գ�";
			return ERROR;
		}
		
		//���������н���
		residentEntity.setName(URLEcoder.decode(residentEntity.getName()));
		
		//��ס����Ϣ�������ݿ�
		if(service.setUser(residentEntity))
			community_id = residentEntity.getCommunity_id()+"";
		else{
			error = "�������˷����쳣��������Ϣʧ�ܣ�������";
			return ERROR;
		}
		return "success_setUser";
	}

	
	/**
	 * ����id��ȡס����Ϣ
	 * @return
	 */
	public String getUser(){
		if(residentEntity==null || residentEntity.getId()==0){
			error = "��������Ϊ�գ�";
			return ERROR;
		}
		
		residentEntity = service.getUser(residentEntity);
		if(residentEntity==null){
			error = "�����������쳣����ȡ������Ϣʧ�ܣ�������";
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
