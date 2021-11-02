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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "providers")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long providerId;
	
	@Column(name = "provider_logo_url")
	private String providerLogoUrl;
	
	@OneToMany
	@JoinColumn(name = "bim_supplier_id")
	private Bim bim;
	
	@OneToMany
	@JoinColumn(name = "supplier_id")
	private User user;
		
	//Constructors
	public Provider() {
		super();
	}

	public Provider(String providerLogoUrl) {
		super();
		this.providerLogoUrl = providerLogoUrl;
	}

	//Getters&Setters
	public String getProviderLogoUrl() {
		return providerLogoUrl;
	}

	public void setProviderLogoUrl(String providerLogoUrl) {
		this.providerLogoUrl = providerLogoUrl;
	}
	
	
			

}
