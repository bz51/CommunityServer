package com.resident.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.core.util.HibernateSessionFactory;
import com.core.util.Pagination;
import com.manager.entity.CommunityEntity;
import com.resident.entity.InfoEntity;
import com.resident.entity.PhoneEntity;
import com.resident.entity.ResidentEntity;
import com.resident.entity.TopicEntity;

public class CommunityDao {


	/**
	 * 根据小区名字搜索相关的小区
	 * @return
	 */
	public Pagination getCommunity(CommunityEntity communityEntity,Pagination pagination) {
//		Session session = HibernateSessionFactory.getSession();
//		session.beginTransaction();
//		String hql = "select c from CommunityEntity c where name like'%"+communityEntity.getName()+"%'";
//		List<CommunityEntity> list  = session.createQuery(hql).list();
//		session.getTransaction().commit();
//		HibernateSessionFactory.closeSession();
//		return list;
		
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
		String hql1 = "select c from CommunityEntity c where name like'%"+communityEntity.getName()+"%'";
		List<CommunityEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		//获取记录总数
		String hql2 = "select count(*) from CommunityEntity where name like'%"+communityEntity.getName()+"%'";
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();
		
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		System.out.println("记录数："+maxRowCount);
		// 封装分页
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}

	
	/**
	 * 获取推送给“我的”公告
	 * @param residentEntity
	 * @return
	 */
	public List<InfoEntity> getMyInfo(ResidentEntity residentEntity) {
		
		List<InfoEntity> list = new ArrayList<InfoEntity>();
		
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		
		//获取本小区公告
		String hql1 = "select i from InfoEntity i where community_id="+residentEntity.getCommunity_id()
				+" and building_id=0"
				+" and area_id=0"
				+" and floor_id=0"
				+" and house_id=0"
				+ " order by time desc";
		list.addAll(session.createQuery(hql1).list());
		
		
		//获取本楼公告
		String hql2 = "select i from InfoEntity i where community_id="+residentEntity.getCommunity_id()
				+" and building_id="+residentEntity.getBuild_id()
				+" and area_id=0"
				+" and floor_id=0"
				+" and house_id=0"
				+ " order by time desc";
		list.addAll(session.createQuery(hql2).list());

		for(InfoEntity i : list){
			System.out.println("list_2:"+i.getContent());
		}
		
		//获取本单元公告
		String hql3 = "select i from InfoEntity i where community_id="+residentEntity.getCommunity_id()
				+" and building_id="+residentEntity.getBuild_id()
				+" and area_id="+residentEntity.getArea_id()
				+" and floor_id=0"
				+" and house_id=0"
				+ " order by time desc";
		list.addAll(session.createQuery(hql3).list());

		for(InfoEntity i : list){
			System.out.println("list_3:"+i.getContent());
		}
		
		//获取本层公告
		String hql4 = "select i from InfoEntity i where community_id="+residentEntity.getCommunity_id()
				+" and building_id="+residentEntity.getBuild_id()
				+" and area_id="+residentEntity.getArea_id()
				+" and floor_id="+residentEntity.getFloor_id()
				+" and house_id=0"
						+ " order by time desc";
		list.addAll(session.createQuery(hql4).list());

		for(InfoEntity i : list){
			System.out.println("list_4:"+i.getContent());
		}
		
		//获取本房间公告
		String hql5 = "select i from InfoEntity i where community_id="+residentEntity.getCommunity_id()
				+" and building_id="+residentEntity.getBuild_id()
				+" and area_id="+residentEntity.getArea_id()
				+" and floor_id="+residentEntity.getFloor_id()
				+" and house_id="+residentEntity.getHouse_id()
				+ " order by time desc";
		list.addAll(session.createQuery(hql5).list());

		for(InfoEntity i : list){
			System.out.println("list_5:"+i.getContent());
		}
		
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		return list;
	}


	/**
	 * 获取本小区便民电话
	 * @param community_id
	 * @return
	 */
	public List<PhoneEntity> getPhone(long community_id) {
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		String hql = "select p from PhoneEntity p where community_id="+community_id;
		List<PhoneEntity> list = session.createQuery(hql).list();
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		return list;
	}

}
