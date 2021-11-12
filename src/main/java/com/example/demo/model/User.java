package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {
	
	
	 @Id	  
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private long Id;
	
	@Column(name = "role_id")
	private long role_Id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "supplier_id")
	private long supplier_Id;
	
	@Column(name = "client_id")
	private long client_Id;
	
	@Transient
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id", referencedColumnName = "client_id")
	private Set<Client> client = new HashSet<>();
	
	@Transient
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "provider_id", referencedColumnName = "client_id")
	private Set<Provider> provider = new HashSet<>();
	
	@Transient
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_role_id", referencedColumnName = "role_id")
	private Set<UserRole> userRole = new HashSet<>();
	

	public User() {
		super();
	}

	public User(long role_Id, String name, String title, String email, long supplier_Id, long client_Id) {
		super();
		this.role_Id = role_Id;
		this.name = name;
		this.title = title;
		this.email = email;
		this.supplier_Id = supplier_Id;
		this.client_Id = client_Id;
	}
	
	

	public User(Set<Client> client, Set<Provider> provider, Set<UserRole> userRole) {
		super();
		this.client = client;
		this.provider = provider;
		this.userRole = userRole;
	}

	public Set<Client> getClient() {
		return client;
	}

	public void setClient(Set<Client> client) {
		this.client = client;
	}

	public Set<Provider> getProvider() {
		return provider;
	}

	public void setProvider(Set<Provider> provider) {
		this.provider = provider;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
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
