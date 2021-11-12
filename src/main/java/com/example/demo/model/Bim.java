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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "bim")
public class Bim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bim_id")
	private long bimId;
	
	@Column(name = "supplier_id")
	private long supplierId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "insight_url")
	private String insight_Url;
	
	@Column(name = "benefits_url")
	private String benefits_Url;
	
	@Column(name = "oneliner_description")
	private String one_Liner_Description;
	
	@Column(name = "short_description")
	private String short_Description;
	
	@Column(name = "rating")
	private String rating;
	
	@Column(name = "layout_type")
	private String layout_Type;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "content_id")
	private long content_Id;	
	
	@Transient
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bim_id", referencedColumnName = "bim_id")
	private Set<BimInstance> bimInstance = new HashSet<>();
	

	public Bim() {
		super();
	}

	public Bim(long supplierId, String title, String insight_Url, String benefits_Url, String one_Liner_Description,
			String short_Description, String rating, String layout_Type, String status, long content_Id) {
		super();
		this.supplierId = supplierId;
		this.title = title;
		this.insight_Url = insight_Url;
		this.benefits_Url = benefits_Url;
		this.one_Liner_Description = one_Liner_Description;
		this.short_Description = short_Description;
		this.rating = rating;
		this.layout_Type = layout_Type;
		this.status = status;
		this.content_Id = content_Id;
	}
	
	

	public Bim(Set<BimInstance> bimInstance) {
		super();
		this.bimInstance = bimInstance;
	}

	public Set<BimInstance> getBimInstance() {
		return bimInstance;
	}

	public void setBimInstance(Set<BimInstance> bimInstance) {
		this.bimInstance = bimInstance;
	}

	public long getBimId() {
		return bimId;
	}

	public void setBimId(long bimId) {
		this.bimId = bimId;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInsight_Url() {
		return insight_Url;
	}

	public void setInsight_Url(String insight_Url) {
		this.insight_Url = insight_Url;
	}

	public String getBenefits_Url() {
		return benefits_Url;
	}

	public void setBenefits_Url(String benefits_Url) {
		this.benefits_Url = benefits_Url;
	}

	public String getOne_Liner_Description() {
		return one_Liner_Description;
	}

	public void setOne_Liner_Description(String one_Liner_Description) {
		this.one_Liner_Description = one_Liner_Description;
	}

	public String getShort_Description() {
		return short_Description;
	}

	public void setShort_Description(String short_Description) {
		this.short_Description = short_Description;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getLayout_Type() {
		return layout_Type;
	}

	public void setLayout_Type(String layout_Type) {
		this.layout_Type = layout_Type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getContent_Id() {
		return content_Id;
	}

	public void setContent_Id(long content_Id) {
		this.content_Id = content_Id;
	}
	
			

}
