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
@Table(name = "providers")
public class Provider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "provider_id")
	private long providerId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "logo_url")
	private String logo_Url;
	
	@Transient
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier_id", referencedColumnName = "provider_id")
	private Set<Bim> bim = new HashSet<>();
	
	public Provider() {
		super();
	}

	public Provider(String name, String logo_Url) {
		super();
		this.name = name;
		this.logo_Url = logo_Url;
	}
	
	

	public Provider(Set<Bim> bim) {
		super();
		this.bim = bim;
	}

	public Set<Bim> getBim() {
		return bim;
	}

	public void setBim(Set<Bim> bim) {
		this.bim = bim;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
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
