package com.example.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "providers")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long providerId;
	
	@Column(name = "provider_name")
	private String providerName;
	@Column(name = "provider_mobile_number")
	private long providerMobile;
	@Column(name = "provider_address")
	private String providerAddress;
	
	@OneToMany(mappedBy = "provider", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<User> user;
	
	public Provider() {
		super();
	}

	public Provider(String providerName, long providerMobile, String providerAddress) {
		super();
		this.providerName = providerName;
		this.providerMobile = providerMobile;
		this.providerAddress = providerAddress;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public long getProviderMobile() {
		return providerMobile;
	}

	public void setProviderMobile(long providerMobile) {
		this.providerMobile = providerMobile;
	}

	public String getProviderAddress() {
		return providerAddress;
	}

	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}
	
	

}
