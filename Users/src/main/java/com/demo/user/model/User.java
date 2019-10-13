package com.demo.user.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String emailId;
	private String password;
	
	public User() {
		
	}
	
	
	public User(String emailId, String password) {
		this.emailId = emailId;
		this.password = password;
	}
	
	@Id
	@Column(name = "email_id", nullable=false)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name = "password", nullable=false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
