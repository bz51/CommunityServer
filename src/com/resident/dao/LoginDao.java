package com.resident.dao;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.core.util.HibernateSessionFactory;
import com.resident.entity.ResidentEntity;

public class LoginDao {

	/**
	 * 检查该用户名是否已被注册
	 */
	public long isLogin(String username) {
		long result;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			String hql = "select count(*) from ResidentEntity where username='"+username+"'";
			result = (Long) session.createQuery(hql).uniqueResult();
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return -1;
		}
		
		return result;
	}

	/**
	 * 注册
	 * @param residentEntity
	 */
	public long signin(ResidentEntity residentEntity) {
		//设置时间
		residentEntity.setTime(new Timestamp(new Date().getTime()));
		
		long id;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			id = (long) session.save(residentEntity);
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return -1;
		}
		return id;
	}

	/**
	 * 登陆
	 * @param residentEntity
	 * @return
	 */
	public ResidentEntity login(ResidentEntity residentEntity) {
		Object o;
		String community_name;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			String hql = "select r from ResidentEntity as r where r.username='"+residentEntity.getUsername()+"' and r.password='"+residentEntity.getPassword()+"' and r.state=0";
			o = session.createQuery(hql).uniqueResult();
			String hql2 = "select b.name from ResidentEntity as a,CommunityEntity as b where a.community_id=b.id and a.username='"+residentEntity.getUsername()+"'";
			community_name = (String) session.createQuery(hql2).uniqueResult();
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
			
			//用户名密码错误
			if(o==null)
				return null;
			
		} catch (HibernateException e) {
			return null;
		}
		
		ResidentEntity entity = (ResidentEntity)o;
		entity.setCommunity_name(community_name);
		return entity;
	}

}
