package com.neom.marketplace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "\"user\"")
public class User {
	
	 @Id	  
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private long id;
	
	@Column(name = "role_id")
	@NotNull(message = "Empty field - role id")
	private long role_Id;
	
	@Column(name = "name")
	@NotEmpty(message = "Empty field - name")
	private String name;
	
	@Column(name = "title")
	@NotEmpty(message = "Empty field - title")
	private String title;
	
	@Column(name = "email")
	@NotEmpty(message = "Empty field - email")
	private String email;
	
	@Column(name = "supplier_id")
	private long supplier_Id;
	
	@Column(name = "client_id")
	private long client_Id;

	public User() {
		
	}

	public User(long id, long role_Id, String name, String title, String email, long supplier_Id, long client_Id) {
		
		this.id = id;
		this.role_Id = role_Id;
		this.name = name;
		this.title = title;
		this.email = email;
		this.supplier_Id = supplier_Id;
		this.client_Id = client_Id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRole_Id() {
		return role_Id;
	}

	public void setRole_Id(long role_Id) {
		this.role_Id = role_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getSupplier_Id() {
		return supplier_Id;
	}

	public void setSupplier_Id(long supplier_Id) {
		this.supplier_Id = supplier_Id;
	}

	public long getClient_Id() {
		return client_Id;
	}

	public void setClient_Id(long client_Id) {
		this.client_Id = client_Id;
	}
	
}
