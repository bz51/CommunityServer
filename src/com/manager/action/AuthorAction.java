package com.manager.action;

import com.core.util.Pagination;
import com.manager.entity.ManagerEntity;
import com.manager.service.AuthorService;
import com.opensymphony.xwork2.ActionSupport;

public class AuthorAction extends ActionSupport {
	// 客户端与服务器传递的参数
	private String result;
	private String error;
	private Pagination pagination;
	private ManagerEntity managerEntity;
	// 全局Service
	private AuthorService service = new AuthorService();
	
	/**
	 * 获取需要授权的用户信息
	 */
	public String getAuthors(){
		if(managerEntity==null || managerEntity.getCommunity_id()==0){
			error = "传递的参数不能为空！";
			return ERROR;
		}
		
		pagination = service.getAuthors(managerEntity,pagination);
		return "success_getAuthors";
	}
	
	/**
	 * 为某一管理员授权
	 * @return
	 */
	public String authorize(){
		if(managerEntity==null || managerEntity.getId()==0){
			error = "传递的参数不能为空！";
			return ERROR;
		}
		
		//授权
		boolean is_author = service.authorize(managerEntity);
		if(is_author)
			result = "yes";
		else
			result = "no";
		return "success_authorize";
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

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public ManagerEntity getManagerEntity() {
		return managerEntity;
	}

	public void setManagerEntity(ManagerEntity managerEntity) {
		this.managerEntity = managerEntity;
	}
	
	
}
