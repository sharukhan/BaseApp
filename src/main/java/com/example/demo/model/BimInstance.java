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
	private long bInsId;
	
	@Column(name = "bim_instance_client_id")
	private long insClientId;
	
	@Column(name = "bim_instance_supplier_id")
	private long insSupplierId;
	
	@Column(name = "bim_Id")
	private long bimId;
	
	@Column(name = "bim_instance_status")
	private String status;
	
	
	  @OneToOne	  
	  @JoinColumn(name = "bim_supplier_id") 
	  private Bim bim;
	  
	  @ManyToMany
	  private Set<Client> client;
	 
		
	//Constructors
	public BimInstance() {
		super();
	}

	public BimInstance(long insClientId, long insSupplierId, String status, long bimId) {
		super();
		this.insClientId = insClientId;
		this.insSupplierId = insSupplierId;
		this.bimId = bimId;
		this.status = status;
	}

	//Getters&Setters
	public long getInsClientId() {
		return insClientId;
	}

	public void setInsClientId(long insClientId) {
		this.insClientId = insClientId;
	}

	public long getInsSupplierId() {
		return insSupplierId;
	}

	public void setInsSupplierId(long insSupplierId) {
		this.insSupplierId = insSupplierId;
	}
	
	public long getBimId() {
		return bimId;
	}

	public void setBimId(long bimId) {
		this.bimId = bimId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
