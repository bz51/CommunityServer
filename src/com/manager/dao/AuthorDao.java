package com.manager.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.core.util.HibernateSessionFactory;
import com.core.util.Pagination;
import com.manager.entity.ManagerEntity;

public class AuthorDao {

	/**
	 * 获取需要授权的用户信息
	 * @param community_id
	 * @param pagination
	 * @return
	 */
	public Pagination getAuthors(long community_id, Pagination pagination) {
		// page为空，则初始化它
		if (pagination == null) {
			pagination = new Pagination();
		}
		// 获取page中的 RowsPerPage、CurrPage
		int rowsPerPage = pagination.getRowsPerPage();
		long currPage = pagination.getCurrPage();
		//计算起始条数
		int rowsCount =  ((int) currPage - 1) * (int) rowsPerPage;
		// 查询分页数据(按默认排序)
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		//获取帖子
		String hql1 = "select m from ManagerEntity m where community_id="+community_id+" and is_super=1 and state=1";
		List<ManagerEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		//获取记录总数
		String hql2 = "select count(*) from ManagerEntity where community_id="+community_id+" and is_super=1 and state=1";
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();
		
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		
		// 封装分页
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}

	
	/**
	 * 授权(将state改为0)
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
