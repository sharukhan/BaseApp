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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bim")
public class Bim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "supplier_id")
	private String supplierId;
	
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
	private String content_Id;
	
	
	  @OneToMany(mappedBy = "bim") 
	  private Set<Provider> providers;
	 
		
	  @OneToOne(mappedBy = "bim") 
	  private BimInstance bimInstance;
		 
	
	

	public Bim() {
		super();
	}

	public Bim(String supplierId, String title, String insight_Url, String benefits_Url, String one_Liner_Description,
			String short_Description, String rating, String layout_Type, String status, String content_Id) {
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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
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

	public String getContent_Id() {
		return content_Id;
	}

	public void setContent_Id(String content_Id) {
		this.content_Id = content_Id;
	}
	
			

}
