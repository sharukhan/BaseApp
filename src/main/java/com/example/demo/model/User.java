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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*@Entity
@Table(name = "users")*/
public class User {
	
	
	 @Id	  
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long id;
	
	@Column(name = "role_id")
	private long role_Id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "supplier_id")
	private String supplier_Id;
	
	@Column(name = "client_id")
	private String client_Id;
	
	
	 @ManyToMany	 
	 @JoinColumn(name = "client_id") 
	 private BimInstance bimInstance;
	 
	 @ManyToOne	 
	 @JoinColumn(name = "id") 
	 private Client client;
	 
	 @ManyToOne	 
	 @JoinColumn(name = "id") 
	 private Provider provider;
	 
	 @ManyToOne
	 @JoinColumn(name = "id")
	 private Set<UserRole> userRole;
	

	public User() {
		super();
	}

	public User(long role_Id, String name, String title, String email, String supplier_Id, String client_Id) {
		super();
		this.role_Id = role_Id;
		this.name = name;
		this.title = title;
		this.email = email;
		this.supplier_Id = supplier_Id;
		this.client_Id = client_Id;
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

	public String getSupplier_Id() {
		return supplier_Id;
	}

	public void setSupplier_Id(String supplier_Id) {
		this.supplier_Id = supplier_Id;
	}

	public String getClient_Id() {
		return client_Id;
	}

	public void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
	}
	
		
	
}
