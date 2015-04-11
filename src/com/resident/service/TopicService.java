package com.resident.service;

import com.core.util.Pagination;
import com.resident.dao.TopicDao;
import com.resident.dao.UserDao;
import com.resident.entity.CommentEntity;
import com.resident.entity.ResidentEntity;
import com.resident.entity.TopicEntity;

public class TopicService {
	private TopicDao dao = new TopicDao();
	
	
	/**
	 * ��ȡ�û�����С������̳����
	 * @param pagination
	 * @param residentEntity
	 * @return
	 */
	public Pagination getTopics(Pagination pagination,ResidentEntity residentEntity) {
		//��ȡ��ǰ�û����ڵ�С��
//		residentEntity = new UserDao().getUser(residentEntity.getId());
		
//		if(residentEntity==null || residentEntity.getCommunity_id()==0)
//			return null;
		
		//��ȡ��С��������
		pagination = dao.getTopics(residentEntity.getCommunity_id(),pagination);
		return pagination;
	}


	/**
	 * ��������id��ȡ����������
	 * @param topicEntity
	 * @param pagination
	 * @return
	 */
	public Pagination getTopicComments(TopicEntity topicEntity,Pagination pagination) {
		return dao.getTopicComments(topicEntity.getId(),pagination);
	}


	/**
	 * ����һ������
	 * @param commentEntity
	 * @return 
	 */
	public boolean postTopicComment(CommentEntity commentEntity) {
		return dao.postTopicComment(commentEntity);
	}


	/**
	 * ��������
	 * @param topicEntity
	 * @return
	 */
	public boolean postTopic(TopicEntity topicEntity) {
		//����community_id����ͨ��post_id��ȡ
		if(topicEntity.getCommunity_id()==0)
			topicEntity.setCommunity_id(new UserDao().getUser(topicEntity.getPost_id()).getCommunity_id());
		
		if(topicEntity.getCommunity_id()==0)
			return false;
		
		//����community_id����ֱ�ӷ�������
		return dao.postTopic(topicEntity);
	}


	
	/**
	 * ���ҷ��������
	 * @param residentEntity
	 * @param pagination
	 * @return
	 */
	public Pagination getMyTopics(TopicEntity topicEntity,Pagination pagination) {
		//���ݷ�����id��role��ѯ����
		return dao.getTopicsByPosterId(topicEntity.getPost_id(),topicEntity.getRole(),pagination);
	}


	
	/**
	 * ���ҵ�����
	 * @param commentEntity
	 * @param pagination
	 * @return
	 */
	public Pagination getMyComTopics(CommentEntity commentEntity,Pagination pagination) {
		return dao.getMyComTopics(commentEntity.getUser_id(),commentEntity.getRole(),pagination);
	}


	/**
	 * ��������id��ȡ����
	 * @param topicEntity
	 * @return
	 */
	public TopicEntity getTopicById(TopicEntity topicEntity) {
		return dao.getTopicById(topicEntity.getId());
	}

}
