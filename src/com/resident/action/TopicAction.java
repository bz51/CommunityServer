package com.resident.action;

import com.core.util.Pagination;
import com.core.util.URLEcoder;
import com.opensymphony.xwork2.ActionSupport;
import com.resident.entity.CommentEntity;
import com.resident.entity.ResidentEntity;
import com.resident.entity.TopicEntity;
import com.resident.service.TopicService;

public class TopicAction extends ActionSupport {
	//�ͻ�������������ݵĲ���
	private Pagination pagination;
	private String error;
	private ResidentEntity residentEntity;
	private TopicEntity topicEntity;
	private CommentEntity commentEntity;
	private String result;
	//ȫ�ֵ�service
	private TopicService service = new TopicService();
	
	
	/**
	 * ��ȡ��С����̳�б�
	 */
	public String getTopics(){
		if(residentEntity==null || residentEntity.getCommunity_id()==0){
			error = "�����������Ϊ�գ�";
			return ERROR;
		}
			
		//��ȡ�û�����С������̳����
		pagination = service.getTopics(pagination,residentEntity);
		
//		if(pagination==null){
//			error = "�����û�id��ȡ���û���Ϣʱ�����쳣��";
//			return ERROR;
//		}
		return "success_getTopics";
	}

	
	/**
	 * ��������id��ȡ����������
	 * @return
	 */
	public String getTopicComments(){
		if(topicEntity==null || topicEntity.getId()==0){
			error = "�����������Ϊ�գ�";
			return ERROR;
		}
		
		//��ȡ����������
		pagination = service.getTopicComments(topicEntity,pagination);
		return "success_getTopicComments";
	}

	
	/**
	 * ����һ������
	 * @return
	 */
	public String postTopicComment(){
		if(commentEntity==null || commentEntity.getTopic_id()==0 ||commentEntity.getUser_id()==0
				|| commentEntity.getContent()==null || commentEntity.getPost_username()==null
				|| commentEntity.getContent().equals("") || commentEntity.getPost_username().equals("")
				){
			error = "�����������Ϊ�գ�";
			return ERROR;
		}
		//����
		commentEntity.setContent(URLEcoder.decode(commentEntity.getContent()));
		commentEntity.setTopic_title(URLEcoder.decode(commentEntity.getTopic_title()));
		//����һ������
		boolean is_post = service.postTopicComment(commentEntity);
		if(is_post)
			result = "yes";
		else
			result = "no";
		return "success_postTopicComment";
	}

	
	/**
	 * ����һ������
	 * @return
	 */
	public String postTopic(){
		if(topicEntity==null
				|| topicEntity.getContent()==null || topicEntity.getPost_id()==0
				|| topicEntity.getPost_username()==null || topicEntity.getTitle()==null){
			error = "�����������Ϊ�գ�";
			return ERROR;
		}
		//����
		topicEntity.setContent(URLEcoder.decode(topicEntity.getContent()));
		topicEntity.setTitle(URLEcoder.decode(topicEntity.getTitle()));
		//��������
		boolean is_post = service.postTopic(topicEntity);
		if(is_post)
			result = "yes";
		else
			result = "no";
		return "success_postTopic";
	}
	
	
	/**
	 * �鿴�ҷ���������
	 * @return
	 */
	public String getMyTopics(){
		if(topicEntity==null || topicEntity.getPost_id()==0){
			error = "�����������Ϊ�գ�";
			return ERROR;
		}
		
		//�鿴�ҷ���������
		pagination = service.getMyTopics(topicEntity,pagination);
		return "success_getMyTopics";
	}
	
	/**
	 * ���ҵ�����
	 * @return
	 */
	public String getMyComTopics(){
		if(commentEntity==null || commentEntity.getUser_id()==0){
			error = "�����������Ϊ�գ�";
			return ERROR;
		}
		
		//���ҵ�����
		pagination = service.getMyComTopics(commentEntity,pagination);
		return "success_getMyComTopics";
	}
	
	
	/**
	 * ��������id��ȡ����
	 * @return
	 */
	public String getTopicById(){
		if(topicEntity==null || topicEntity.getId()==0){
			error = "�����������Ϊ�գ�";
			return ERROR;
		}
		
		//��������id��ȡ����
		topicEntity = service.getTopicById(topicEntity);
		if(topicEntity==null){
			error = "��ѯʧ�ܣ�";
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
