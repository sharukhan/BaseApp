package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userRoleId;
	
	@Column(name = "user_role")
	private String userRole;
	
	
	  @ManyToOne  
	  @JoinColumn(name = "user_role_id") 
	  private User user;
	 

	//Constructors
	public UserRole() {
		super();
	}

	public UserRole(String userRole) {
		super();
		this.userRole = userRole;
	}

	//Getters&Setters
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	

}
