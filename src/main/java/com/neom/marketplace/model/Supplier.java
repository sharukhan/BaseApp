package com.neom.marketplace.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "supplier")
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	@NotEmpty(message = "Empty field - name")
	private String name;
	
	@Column(name = "logo_url")
	@NotEmpty(message = "Empty field - logo url")
	private String logoUrl;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
	private List<Bim> bim;
	
//	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//			 CascadeType.DETACH, CascadeType.REFRESH})
//	@JoinColumn(name = "supplier_id")
//	private List<User> users;
	
	public Supplier() {
		
	}

	public Supplier(long id, String name, String logoUrl, List<Bim> bim) {
		
		this.id = id;
		this.name = name;
		this.logoUrl = logoUrl;
		this.bim = bim;
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

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public List<Bim> getBim() {
		return bim;
	}

	public void setBim(List<Bim> bim) {
		this.bim = bim;
	}

//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
	
}
