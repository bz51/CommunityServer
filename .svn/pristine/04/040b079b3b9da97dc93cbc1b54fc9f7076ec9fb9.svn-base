package com.resident.action;

import com.core.util.Pagination;
import com.core.util.URLEcoder;
import com.opensymphony.xwork2.ActionSupport;
import com.resident.entity.CommentEntity;
import com.resident.entity.ResidentEntity;
import com.resident.entity.TopicEntity;
import com.resident.service.TopicService;

public class TopicAction extends ActionSupport {
	//客户端与服务器传递的参数
	private Pagination pagination;
	private String error;
	private ResidentEntity residentEntity;
	private TopicEntity topicEntity;
	private CommentEntity commentEntity;
	private String result;
	//全局的service
	private TopicService service = new TopicService();
	
	
	/**
	 * 获取本小区论坛列表
	 */
	public String getTopics(){
		if(residentEntity==null || residentEntity.getCommunity_id()==0){
			error = "传入参数不能为空！";
			return ERROR;
		}
			
		//获取用户所在小区的论坛帖子
		pagination = service.getTopics(pagination,residentEntity);
		
//		if(pagination==null){
//			error = "根据用户id获取该用户信息时发生异常！";
//			return ERROR;
//		}
		return "success_getTopics";
	}

	
	/**
	 * 根据帖子id获取该贴的评论
	 * @return
	 */
	public String getTopicComments(){
		if(topicEntity==null || topicEntity.getId()==0){
			error = "传入参数不能为空！";
			return ERROR;
		}
		
		//获取该贴的评论
		pagination = service.getTopicComments(topicEntity,pagination);
		return "success_getTopicComments";
	}

	
	/**
	 * 发表一个评论
	 * @return
	 */
	public String postTopicComment(){
		if(commentEntity==null || commentEntity.getTopic_id()==0 ||commentEntity.getUser_id()==0
				|| commentEntity.getContent()==null || commentEntity.getPost_username()==null
				|| commentEntity.getContent().equals("") || commentEntity.getPost_username().equals("")
				){
			error = "传入参数不能为空！";
			return ERROR;
		}
		//解码
		commentEntity.setContent(URLEcoder.decode(commentEntity.getContent()));
		commentEntity.setTopic_title(URLEcoder.decode(commentEntity.getTopic_title()));
		//发表一个评论
		boolean is_post = service.postTopicComment(commentEntity);
		if(is_post)
			result = "yes";
		else
			result = "no";
		return "success_postTopicComment";
	}

	
	/**
	 * 发表一个帖子
	 * @return
	 */
	public String postTopic(){
		if(topicEntity==null
				|| topicEntity.getContent()==null || topicEntity.getPost_id()==0
				|| topicEntity.getPost_username()==null || topicEntity.getTitle()==null){
			error = "传入参数不能为空！";
			return ERROR;
		}
		//解码
		topicEntity.setContent(URLEcoder.decode(topicEntity.getContent()));
		topicEntity.setTitle(URLEcoder.decode(topicEntity.getTitle()));
		//发布帖子
		boolean is_post = service.postTopic(topicEntity);
		if(is_post)
			result = "yes";
		else
			result = "no";
		return "success_postTopic";
	}
	
	
	/**
	 * 查看我发布的帖子
	 * @return
	 */
	public String getMyTopics(){
		if(topicEntity==null || topicEntity.getPost_id()==0){
			error = "传入参数不能为空！";
			return ERROR;
		}
		
		//查看我发布的帖子
		pagination = service.getMyTopics(topicEntity,pagination);
		return "success_getMyTopics";
	}
	
	/**
	 * 查我的评论
	 * @return
	 */
	public String getMyComTopics(){
		if(commentEntity==null || commentEntity.getUser_id()==0){
			error = "传入参数不能为空！";
			return ERROR;
		}
		
		//查我的评论
		pagination = service.getMyComTopics(commentEntity,pagination);
		return "success_getMyComTopics";
	}
	
	
	/**
	 * 根据帖子id获取帖子
	 * @return
	 */
	public String getTopicById(){
		if(topicEntity==null || topicEntity.getId()==0){
			error = "传入参数不能为空！";
			return ERROR;
		}
		
		//根据帖子id获取帖子
		topicEntity = service.getTopicById(topicEntity);
		if(topicEntity==null){
			error = "查询失败！";
			return ERROR;
		}
		return "success_getTopicById";
	}
	
	
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public ResidentEntity getResidentEntity() {
		return residentEntity;
	}


	public void setResidentEntity(ResidentEntity residentEntity) {
		this.residentEntity = residentEntity;
	}


	public TopicEntity getTopicEntity() {
		return topicEntity;
	}


	public void setTopicEntity(TopicEntity topicEntity) {
		this.topicEntity = topicEntity;
	}


	public CommentEntity getCommentEntity() {
		return commentEntity;
	}


	public void setCommentEntity(CommentEntity commentEntity) {
		this.commentEntity = commentEntity;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	
	
	
}
