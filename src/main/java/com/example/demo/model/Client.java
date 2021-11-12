package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_id")
	private long clientId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "industry")
	private String industry;
	
	@Column(name = "country")
	private String country;
	
	public Client() {
		super();
	}

	public Client(String name, String industry, String country) {
		super();
		this.name = name;
		this.industry = industry;
		this.country = country;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
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
		

}
