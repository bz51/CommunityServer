package com.manager.service;

import com.core.util.Pagination;
import com.manager.dao.AuthorDao;
import com.manager.entity.ManagerEntity;

public class AuthorService {
	private AuthorDao dao = new AuthorDao();
	
	/**
	 * ��ȡ��Ҫ��Ȩ���û���Ϣ
	 * @param managerEntity
	 * @param pagination
	 * @return
	 */
	public Pagination getAuthors(ManagerEntity managerEntity,Pagination pagination) {
		return dao.getAuthors(managerEntity.getCommunity_id(),pagination);
	}

	
	/**
	 * ��Ȩ
	 * @param managerEntity
	 * @return
	 */
	public boolean authorize(ManagerEntity managerEntity) {
		return dao.authorize(managerEntity.getId());
	}

}
