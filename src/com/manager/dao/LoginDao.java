package com.manager.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.core.util.HibernateSessionFactory;
import com.manager.entity.ManagerEntity;

public class LoginDao {

	/**
	 * ��½
	 * @param managerEntity
	 * @return
	 */
	public ManagerEntity login(ManagerEntity managerEntity) {
		Object o;
		String community_name;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			String hql = "select m from ManagerEntity m where username='"+managerEntity.getUsername()+"' and password='"+managerEntity.getPassword()+"' and state=0";
			o = session.createQuery(hql).uniqueResult();
			String hql2 = "select b.name from ManagerEntity as a,CommunityEntity as b where a.community_id=b.id and a.username='"+managerEntity.getUsername()+"'";
			community_name = (String) session.createQuery(hql2).uniqueResult();
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return null;
		}
		
		//�û��������벻��ȷ
		if(o==null)
			return null;
		ManagerEntity entity = (ManagerEntity)o;
		entity.setCommunity_name(community_name);
		return entity;
	}

	/**
	 * ��ע����Ϣ�������ݿ�
	 * @param managerEntity
	 * @return
	 */
	public long signin(ManagerEntity managerEntity) {
		//����ʱ�䡢����Ϊ��ͨ����Ա
		managerEntity.setTime(new Timestamp(new Date().getTime()));
		managerEntity.setIs_super(1);
		managerEntity.setState(1);
		
		long user_id;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			user_id = (long) session.save(managerEntity);
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return -1;
		}
		return user_id;
	}

	
	/**
	 * ���ݿ��и��û����ĸ���
	 * @param username
	 * @return
	 */
	public long isLogin(String username) {
		long result;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			String hql = "select count(*) from ManagerEntity where username='"+username+"'";
			result = (Long) session.createQuery(hql).uniqueResult();
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return -1;
		}
		
		return result;
	}

	
}
