package com.example.demo.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bim_instance")
public class BimInstance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "client_id")
	private long client_Id;
	
	@Column(name = "supplier_id")
	private long supplier_Id;
	
	@Column(name = "Id")
	private long bim_Id;
	
	@Column(name = "status")
	private String status;
	
	
	 @OneToOne	  
	 @JoinColumn(name = "bim_supplier_id") 
	 private Bim bim;
	  
	 @ManyToMany 
	 private Set<Client> client;
	 
	
	

	public BimInstance() {
		super();
	}

	public BimInstance(long client_Id, long supplier_Id, long bim_Id, String status) {
		super();
		this.client_Id = client_Id;
		this.supplier_Id = supplier_Id;
		this.bim_Id = bim_Id;
		this.status = status;
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

	public long getBim_Id() {
		return bim_Id;
	}

	public void setBim_Id(long bim_Id) {
		this.bim_Id = bim_Id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		

}
