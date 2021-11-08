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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "providers")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "logo_url")
	private String logo_Url;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id", nullable = false) 
	private Bim bim;
	 
	@OneToMany 
	private Set<User> user;
	
	public Provider() {
		super();
	}

	public Provider(String name, String logo_Url) {
		super();
		this.name = name;
		this.logo_Url = logo_Url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo_Url() {
		return logo_Url;
	}

	public void setLogo_Url(String logo_Url) {
		this.logo_Url = logo_Url;
	}
		
			

}
