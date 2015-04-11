package com.resident.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="phone")
public class PhoneEntity {
	private long id;
	private long community_id;
	private String phone;
	private String text;
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getCommunity_id() {
		return community_id;
	}
	public void setCommunity_id(long community_id) {
		this.community_id = community_id;
	}
	
	
}
