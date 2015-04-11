package com.resident.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.core.util.HibernateSessionFactory;
import com.core.util.Pagination;
import com.resident.entity.CommentEntity;
import com.resident.entity.TopicEntity;

public class TopicDao {

	
	/**
	 * 获取当前小区的帖子
	 * @param community_id
	 * @return
	 */
	public Pagination getTopics(long community_id,Pagination pagination) {
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
		String hql1 = "select t from TopicEntity t where community_id="+community_id+" order by time desc";
		List<TopicEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		//获取记录总数
		String hql2 = "select count(*) from TopicEntity where community_id="+community_id;
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();
		
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		
		// 封装分页
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}

	
	/**
	 * 根据帖子id获取该贴的评论
	 * @param id
	 * @param pagination
	 * @return
	 */
	public Pagination getTopicComments(long id, Pagination pagination) {
		// page为空，则初始化它
		if (pagination == null) {
			pagination = new Pagination();
		}
		// 获取page中的 RowsPerPage、CurrPage
		int rowsPerPage = pagination.getRowsPerPage();
		long currPage = pagination.getCurrPage();
		// 计算起始条数
		int rowsCount = ((int) currPage - 1) * (int) rowsPerPage;
		// 查询分页数据(按默认排序)
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		// 获取帖子
		String hql1 = "select c from CommentEntity c where topic_id="+id;
		List<CommentEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		
		// 获取记录总数
		String hql2 = "select count(*) from CommentEntity where topic_id="+id;
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();

		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();

		// 封装分页
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}


	/**
	 * 发表一个评论
	 * @param commentEntity
	 * @return
	 */
	public boolean postTopicComment(CommentEntity commentEntity) {
		try {
			//设置时间
			commentEntity.setTime(new Timestamp(new Date().getTime()));
			
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(commentEntity);
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return false;
		}
		return true;
	}


	/**
	 * 发布帖子
	 * @param topicEntity
	 * @return
	 */
	public boolean postTopic(TopicEntity topicEntity) {
		try {
			//设置时间
			topicEntity.setTime(new Timestamp(new Date().getTime()));
			
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			session.save(topicEntity);
			session.getTransaction().commit();
			HibernateSessionFactory.closeSession();
		} catch (HibernateException e) {
			return false;
		}
		return true;
	}


	/**
	 * 根据发布者id查询帖子
	 * @param id
	 * @param pagination
	 * @return
	 */
	public Pagination getTopicsByPosterId(long id,int role, Pagination pagination) {
		// page为空，则初始化它
		if (pagination == null) {
			pagination = new Pagination();
		}
		// 获取page中的 RowsPerPage、CurrPage
		int rowsPerPage = pagination.getRowsPerPage();
		long currPage = pagination.getCurrPage();
		// 计算起始条数
		int rowsCount = ((int) currPage - 1) * (int) rowsPerPage;
		// 查询分页数据(按默认排序)
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		// 获取帖子
		String hql1 = "select t from TopicEntity t where post_id="+id +" and role="+role+" order by time desc";
		List<TopicEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		
		// 获取记录总数
		String hql2 = "select count(*) from TopicEntity where post_id="+id +" and role="+role;
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();

		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();

		// 封装分页
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}


	/**
	 * 查我的评论
	 * @param user_id
	 * @param role
	 * @param pagination
	 * @return
	 */
	public Pagination getMyComTopics(long user_id, int role,Pagination pagination) {
		// page为空，则初始化它
		if (pagination == null) {
			pagination = new Pagination();
		}
		// 获取page中的 RowsPerPage、CurrPage
		int rowsPerPage = pagination.getRowsPerPage();
		long currPage = pagination.getCurrPage();
		// 计算起始条数
		int rowsCount = ((int) currPage - 1) * (int) rowsPerPage;
		// 查询分页数据(按默认排序)
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		// 获取帖子
		String hql1 = "select c from CommentEntity c where user_id="+user_id +" and role="+role+" order by time desc";
		List<TopicEntity> lists = session.createQuery(hql1).setFirstResult(rowsCount).setMaxResults(rowsPerPage).list();
		
		// 获取记录总数
		String hql2 = "select count(*) from CommentEntity where user_id="+user_id +" and role="+role;
		long maxRowCount = (long) session.createQuery(hql2).uniqueResult();

		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();

		// 封装分页
		pagination.setData(lists);
		pagination.setMaxRowCount(maxRowCount);
		return pagination;
	}


	/**
	 * 根据帖子id获取帖子
	 * @param id
	 * @return
	 */
	public TopicEntity getTopicById(long id) {
		Session session = HibernateSessionFactory.getSession();
		session.beginTransaction();
		Object o = session.get(TopicEntity.class, id);
		session.getTransaction().commit();
		HibernateSessionFactory.closeSession();
		
		if(o==null)
			return null;
		else
			return (TopicEntity)o;
	}

}
