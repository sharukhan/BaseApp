package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bim")
public class Bim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bimId;
	
	@Column(name = "bim_name")
	private String bimName;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Bim() {
		super();
	}

	
	public Bim(String bimName, User user) {
		super();
		this.bimName = bimName;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBimName() {
		return bimName;
	}

	public void setBimName(String bimName) {
		this.bimName = bimName;
	}
	
	

}
