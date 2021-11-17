package com.neom.marketplace.model;

import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bim")
public class Bim {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "supplier_id")
	@JsonIgnore
	private Supplier supplier;
	
	@Transient
	private long supplier_id;
	
	@Column(name = "title")
	@NotEmpty(message = "Empty field - title")
	private String title;
	
	@Column(name = "insight_url")
	@NotEmpty(message = "Empty field - insight url")
	private String insight_Url;
	
	@Column(name = "benefits_url")
	@NotEmpty(message = "Empty field - benefits url")
	private String benefits_Url;
	
	@Column(name = "oneliner_description")
	@NotEmpty(message = "Empty field - oneliner description")
	private String one_Liner_Description;
	
	@Column(name = "short_description")
	@NotEmpty(message = "Empty field - short description")
	private String short_Description;
	
	@Column(name = "rating")
	@NotEmpty(message = "Empty field - rating")
	private String rating;
	
	@Column(name = "layout_type")
	@NotEmpty(message = "Empty field - layout type")
	private String layout_Type;
	
	@Column(name = "status")
	@NotEmpty(message = "Empty field - status")
	private String status;
	
	@Column(name = "content_id")
	@NotNull(message = "Empty field - content id")
	private long content_Id;	
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
			mappedBy = "bim")
	private List<BimInstance> bimInstance;
	

	public Bim() {
		
	}


	public Bim(long id, Supplier supplier, String title, String insight_Url, String benefits_Url,
			String one_Liner_Description, String short_Description, String rating, String layout_Type, String status,
			long content_Id, List<BimInstance> bimInstance, long supplier_id) {
		
		this.id = id;
		this.supplier = supplier;
		this.supplier_id = supplier_id;
		this.title = title;
		this.insight_Url = insight_Url;
		this.benefits_Url = benefits_Url;
		this.one_Liner_Description = one_Liner_Description;
		this.short_Description = short_Description;
		this.rating = rating;
		this.layout_Type = layout_Type;
		this.status = status;
		this.content_Id = content_Id;
		this.bimInstance = bimInstance;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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


	public List<BimInstance> getBimInstance() {
		return bimInstance;
	}


	public void setBimInstance(List<BimInstance> bimInstance) {
		this.bimInstance = bimInstance;
	}


	public long getSupplier_id() {
		return supplier_id;
	}


	public void setSupplier_id(long supplier_id) {
		this.supplier_id = supplier_id;
	}

	
}
