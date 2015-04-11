package com.resident.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="topic")
public class TopicEntity {
	private long id;
	private String title;
	private String content;
	private long post_id;
	private String post_username;
	private int role;
	private Timestamp time;
	private int state;
	private long community_id;

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getPost_id() {
		return post_id;
	}
	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	public String getPost_username() {
		return post_username;
	}
	public void setPost_username(String post_username) {
		this.post_username = post_username;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getCommunity_id() {
		return community_id;
	}
	public void setCommunity_id(long community_id) {
		this.community_id = community_id;
	}
	
	
}
