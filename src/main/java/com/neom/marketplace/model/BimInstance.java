package com.neom.marketplace.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bim_instance")
public class BimInstance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "client_id")
	@NotNull(message = "Empty field - client id")
	private long client_Id;
	
	@Column(name = "supplier_id")
	@NotNull(message = "Empty field - supplier id")
	private long supplier_Id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "bim_id")
	@JsonIgnore
	private Bim bim;
	
	@Transient
	@NotNull(message = "Empty field - bim id")
	private long bim_id;
	
	@Column(name = "status")
	@NotEmpty(message = "Empty field - status")
	private String status;
	

	public BimInstance() {
		
	}


	public BimInstance(long id, long client_Id, long supplier_Id, 
			Bim bim, String status, long bim_id) {
		
		this.id = id;
		this.client_Id = client_Id;
		this.supplier_Id = supplier_Id;
		this.bim = bim;
		this.status = status;
		this.bim_id = bim_id;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getClient_Id() {
		return client_Id;
	}


	public void setClient_Id(long client_Id) {
		this.client_Id = client_Id;
	}


	public long getSupplier_Id() {
		return supplier_Id;
	}


	public void setSupplier_Id(long supplier_Id) {
		this.supplier_Id = supplier_Id;
	}


	public Bim getBim() {
		return bim;
	}


	public void setBim(Bim bim) {
		this.bim = bim;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	public long getBim_id() {
		return bim_id;
	}


	public void setBim_id(long bim_id) {
		this.bim_id = bim_id;
	}
}
