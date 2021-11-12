package com.example.demo.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id")
	private long userRoleId;
	
	@Column(name = "name")
	private String name;

	public UserRole() {
		super();
	}

	public UserRole(String name) {
		super();
		this.name = name;
	}

	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
