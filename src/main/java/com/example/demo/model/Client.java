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

/*@Entity
@Table(name = "client")*/
public class Client {
	
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "name")
	private String industry;
	
	@Column(name = "country")
	private String country;
	
	
	@OneToMany(mappedBy = "client") 
	private Set<User> user;
	  
	@ManyToMany 
	private Set<BimInstance> bimInstance;
	 
	
	public Client() {
		super();
	}

	public Client(String name, String industry, String country) {
		super();
		this.name = name;
		this.industry = industry;
		this.country = country;
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
