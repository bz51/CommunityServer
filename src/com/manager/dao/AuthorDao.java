package com.manager.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.core.util.HibernateSessionFactory;
import com.core.util.Pagination;
import com.manager.entity.ManagerEntity;

public class AuthorDao {

	/**
	 * ��ȡ��Ҫ��Ȩ���û���Ϣ
	 * @param community_id
	 * @param pagination
	 * @return
	 */
	public Pagination getAuthors(long community_id, Pagination pagination) {
		// pageΪ�գ����ʼ����
		if (pagination == null) {
			pagination = new Pagination();
		}
		// ��ȡpage�е� RowsPerPage��CurrPage
		int rowsPerPage = pagination.getRowsPerPage();
		long currPage = pagination.getCurrPage();
		//������ʼ����
		int rowsCount =  ((int) currPage - 1) * (int) rowsPerPage;
		// ��ѯ��ҳ����(��Ĭ������)
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		//��ȡ����
		String hql1 = "select m from ManagerEntity m where community_id="+community_id+" and is_super=1 and state=1";
		List<ManagerEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		//��ȡ��¼����
		String hql2 = "select count(*) from ManagerEntity where community_id="+community_id+" and is_super=1 and state=1";
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();
		
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		
		// ��װ��ҳ
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}

	
	/**
	 * ��Ȩ(��state��Ϊ0)
	 * @param id
	 * @return
	 */
	public boolean authorize(long id) {
		int result;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			String hql = "update ManagerEntity set state=0 where id="+id;
			result = session.createQuery(hql).executeUpdate();
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			result = -1;
		}
		
		return result==1?true:false;
	}

}
