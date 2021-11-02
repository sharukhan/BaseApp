package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_role_id")
	private long userRoleId;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_title")
	private String userTitle;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "supplier_id")
	private String supplierId;
	
	@Column(name = "client_id")
	private String clientId;
	
	@ManyToMany
	@JoinColumn(name = "bim_instance_client_id")
	private BimInstance bimInstance;
	
	//Constructors
	public User() {
		super();
	}

	public User(long userRoleId, String userName, String userTitle, String userEmail, String supplierId,
			String clientId) {
		super();
		this.userRoleId = userRoleId;
		this.userName = userName;
		this.userTitle = userTitle;
		this.userEmail = userEmail;
		this.supplierId = supplierId;
		this.clientId = clientId;
	}

	//Getters&Setters
	public long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	
	

}
