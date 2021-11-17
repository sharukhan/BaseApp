package com.neom.marketplace.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "client")
public class Client {
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	@NotEmpty(message = "Empty field - name")
	private String name;
	
	@Column(name = "industry")
	@NotEmpty(message = "Empty field - industry")
	private String industry;
	
	@Column(name = "country")
	@NotEmpty(message = "Empty field - country")
	private String country;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="client_id")
	private List<BimInstance> bimInstances;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "client_id")
//	private List<User> users;
	
	public Client() {
		
	}

	public Client(long id, String name, String industry, String country, List<BimInstance> bimInstances) {
		this.id = id;
		this.name = name;
		this.industry = industry;
		this.country = country;
		this.bimInstances = bimInstances;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<BimInstance> getBimInstances() {
		return bimInstances;
	}

	public void setBimInstances(List<BimInstance> bimInstances) {
		this.bimInstances = bimInstances;
	}

//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
	
}
