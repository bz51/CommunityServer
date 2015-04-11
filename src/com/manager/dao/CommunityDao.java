package com.manager.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.core.util.HibernateSessionFactory;
import com.core.util.Pagination;
import com.resident.entity.InfoEntity;
import com.resident.entity.PhoneEntity;
import com.resident.entity.ResidentEntity;
import com.resident.entity.TopicEntity;

public class CommunityDao {

	/**
	 * ��������
	 * @param infoEntity
	 * @return
	 */
	public boolean postInfo(InfoEntity infoEntity) {
		//����ʱ��
		infoEntity.setTime(new Timestamp(new Date().getTime()));
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(infoEntity);
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return false;
		}
		return true;
	}

	
	/**
	 * ��ȡ��С�����й���
	 * @param community_id
	 * @param pagination 
	 * @return
	 */
	public Pagination getInfos(long community_id, Pagination pagination) {
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
		String hql1 = "select i from InfoEntity i where community_id="+community_id+" order by time desc";
		List<InfoEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		//��ȡ��¼����
		String hql2 = "select count(*) from InfoEntity where community_id="+community_id;
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();
		
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		
		// ��װ��ҳ
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}


	/**
	 * ����ס��
	 * @param residentEntity
	 * @param pagination
	 * @return
	 */
	public Pagination searchUser(ResidentEntity residentEntity,Pagination pagination) {
		//��Ĭ��ֵ����>���ַ���""
		Map<String,String> map = this.setDefaultEntity(residentEntity);
		
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
		String hql1 = "select r from ResidentEntity r where community_id="+residentEntity.getCommunity_id()
				+" and build_id ='"+map.get("build_id")
				+"' and area_id ='"+map.get("area_id")
				+"' and floor_id ='"+map.get("floor_id")
				+"' and house_id ='"+map.get("house_id")
				+"' and name ='"+residentEntity.getName()+"'";
//				+" and build_id like '%"+map.get("build_id")
//				+"%' and area_id like '%"+map.get("area_id")
//				+"%' and floor_id like '%"+map.get("floor_id")
//				+"%' and house_id like '%"+map.get("house_id")
//				+"%' and name like '%"+residentEntity.getName()+"%'";
		List<ResidentEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		//��ȡ��¼����
		String hql2 = "select count(*) from ResidentEntity where community_id="+residentEntity.getCommunity_id()
				+" and build_id ='"+map.get("build_id")
				+"' and area_id ='"+map.get("area_id")
				+"' and floor_id ='"+map.get("floor_id")
				+"' and house_id ='"+map.get("house_id")
				+"' and name ='"+residentEntity.getName()+"'";
//				+" and build_id like '%"+map.get("build_id")
//				+"%' and area_id like '%"+map.get("area_id")
//				+"%' and floor_id like '%"+map.get("floor_id")
//				+"%' and house_id like '%"+map.get("house_id")
//				+"%' and name like '%"+residentEntity.getName()+"%'";
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();
		
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		
		// ��װ��ҳ
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}
	
	/**
	 * ��ResidentEntity�е�Ĭ��ֵת���ɿ��ַ���""
	 */
	private Map<String,String> setDefaultEntity(ResidentEntity residentEntity){
		Map<String,String> map = new HashMap<String,String>();
		
		if(residentEntity.getArea_id()==0)
			map.put("area_id", "");
		else
			map.put("area_id", residentEntity.getArea_id()+"");
		if(residentEntity.getBuild_id()==0)
			map.put("build_id", "");
		else
			map.put("build_id", residentEntity.getBuild_id()+"");
		if(residentEntity.getFloor_id()==0)
			map.put("floor_id", "");
		else
			map.put("floor_id", residentEntity.getFloor_id()+"");
		if(residentEntity.getHouse_id()==0)
			map.put("house_id", "");
		else
			map.put("house_id", residentEntity.getHouse_id()+"");
		
		return map;
	}


	/**
	 * ����һ������绰
	 * @param phoneEntity
	 * @return
	 */
	public boolean postPhone(PhoneEntity phoneEntity) {
		try {
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(phoneEntity);
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return false;
		}
		return true;
	}

}
