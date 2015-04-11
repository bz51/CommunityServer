package com.resident.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.core.util.HibernateSessionFactory;
import com.manager.entity.CommunityEntity;
import com.resident.entity.ResidentEntity;

public class UserDao {

	/**
	 * 将住户信息存入数据库
	 * @param residentEntity
	 * @return
	 */
	public int setUser(ResidentEntity residentEntity) {
		int result;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			
			String hql = "update ResidentEntity set community_id="+residentEntity.getCommunity_id()
					+" , build_id="+residentEntity.getBuild_id()
					+" , area_id="+residentEntity.getArea_id()
					+" , floor_id="+residentEntity.getFloor_id()
					+" , house_id="+residentEntity.getHouse_id()
					+" , name='"+residentEntity.getName()+"'"
					+" , phone='"+residentEntity.getPhone()+"'"
					+" , is_owner="+residentEntity.getIs_owner()
					+" where id="+residentEntity.getId();
			result = session.createQuery(hql).executeUpdate();
			
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return -1;
		}
		return result;
	}

	
	
	
	/**
	 * 根据住户id获取住户信息
	 * @param id
	 * @return
	 */
	public ResidentEntity getUser(long id) {
		Object o;
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			o = session.get(ResidentEntity.class, id);
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return null;
		}
		
		if(o==null)
			return null;
		else
			return (ResidentEntity) o;
	}


}
