package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long clientId;
	
	@Column(name = "client_name")
	private String clientName;
	
	@Column(name = "industry_name")
	private String industryName;
	
	@Column(name = "client_country")
	private String clientCountry;
	
	
	 @OneToMany(mappedBy = "client") 
	 private Set<User> user;
	 
	 @ManyToMany
	 private Set<BimInstance> bimInstance;
	
	
	//Constructors
	public Client() {
		super();
	}

	public Client(String clientName, String industryName, String clientCountry) {
		super();
		this.clientName = clientName;
		this.industryName = industryName;
		this.clientCountry = clientCountry;
	}

	//Getters&Setters
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getClientCountry() {
		return clientCountry;
	}

	public void setClientCountry(String clientCountry) {
		this.clientCountry = clientCountry;
	}
	
	

}
