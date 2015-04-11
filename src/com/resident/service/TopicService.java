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
	 * 获取用户所在小区的论坛帖子
	 * @param pagination
	 * @param residentEntity
	 * @return
	 */
	public Pagination getTopics(Pagination pagination,ResidentEntity residentEntity) {
		//获取当前用户所在的小区
//		residentEntity = new UserDao().getUser(residentEntity.getId());
		
//		if(residentEntity==null || residentEntity.getCommunity_id()==0)
//			return null;
		
		//获取该小区的帖子
		pagination = dao.getTopics(residentEntity.getCommunity_id(),pagination);
		return pagination;
	}


	/**
	 * 根据帖子id获取该贴的评论
	 * @param topicEntity
	 * @param pagination
	 * @return
	 */
	public Pagination getTopicComments(TopicEntity topicEntity,Pagination pagination) {
		return dao.getTopicComments(topicEntity.getId(),pagination);
	}


	/**
	 * 发表一个评论
	 * @param commentEntity
	 * @return 
	 */
	public boolean postTopicComment(CommentEntity commentEntity) {
		return dao.postTopicComment(commentEntity);
	}


	/**
	 * 发布帖子
	 * @param topicEntity
	 * @return
	 */
	public boolean postTopic(TopicEntity topicEntity) {
		//若无community_id，则通过post_id获取
		if(topicEntity.getCommunity_id()==0)
			topicEntity.setCommunity_id(new UserDao().getUser(topicEntity.getPost_id()).getCommunity_id());
		
		if(topicEntity.getCommunity_id()==0)
			return false;
		
		//若有community_id，则直接发布帖子
		return dao.postTopic(topicEntity);
	}


	
	/**
	 * 查我发表的帖子
	 * @param residentEntity
	 * @param pagination
	 * @return
	 */
	public Pagination getMyTopics(TopicEntity topicEntity,Pagination pagination) {
		//根据发布者id和role查询帖子
		return dao.getTopicsByPosterId(topicEntity.getPost_id(),topicEntity.getRole(),pagination);
	}


	
	/**
	 * 查我的评论
	 * @param commentEntity
	 * @param pagination
	 * @return
	 */
	public Pagination getMyComTopics(CommentEntity commentEntity,Pagination pagination) {
		return dao.getMyComTopics(commentEntity.getUser_id(),commentEntity.getRole(),pagination);
	}


	/**
	 * 根据帖子id获取帖子
	 * @param topicEntity
	 * @return
	 */
	public TopicEntity getTopicById(TopicEntity topicEntity) {
		return dao.getTopicById(topicEntity.getId());
	}

}
