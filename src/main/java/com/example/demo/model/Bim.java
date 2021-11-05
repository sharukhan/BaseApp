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
	private long bimId;
	
	@Column(name = "bim_supplier_id")
	private String bimSupplierId;
	
	@Column(name = "bim_title")
	private String bimTitle;
	
	@Column(name = "bim_insight_url")
	private String bimInsightUrl;
	
	@Column(name = "bim_benefits_url")
	private String bimBenefitsUrl;
	
	@Column(name = "bim_oneliner_description")
	private String bimOneLinerDescription;
	
	@Column(name = "bim_rating")
	private String bimRating;
	
	@Column(name = "bim_layout_type")
	private String bimLayoutType;
	
	@Column(name = "bim_status")
	private String bimStatus;
	
	@Column(name = "bim_content_id")
	private String bimContentId;
	
		@OneToMany(mappedBy = "bim")
		private Set<Provider> providers;
		
		@OneToOne(mappedBy = "bim")
		private BimInstance bimInstance;
		
	
	//Constructors
	public Bim() {
		super();
	}

	public Bim(String bimSupplierId, String bimTitle, String bimInsightUrl, String bimBenefitsUrl,
			String bimOneLinerDescription, String bimRating, String bimLayoutType, String bimStatus,
			String bimContentId, User user, Provider provider) {
		super();
		this.bimSupplierId = bimSupplierId;
		this.bimTitle = bimTitle;
		this.bimInsightUrl = bimInsightUrl;
		this.bimBenefitsUrl = bimBenefitsUrl;
		this.bimOneLinerDescription = bimOneLinerDescription;
		this.bimRating = bimRating;
		this.bimLayoutType = bimLayoutType;
		this.bimStatus = bimStatus;
		this.bimContentId = bimContentId;
	}

	//Getters&Setters
	public String getBimSupplierId() {
		return bimSupplierId;
	}

	public void setBimSupplierId(String bimSupplierId) {
		this.bimSupplierId = bimSupplierId;
	}

	public String getBimTitle() {
		return bimTitle;
	}

	public void setBimTitle(String bimTitle) {
		this.bimTitle = bimTitle;
	}

	public String getBimInsightUrl() {
		return bimInsightUrl;
	}

	public void setBimInsightUrl(String bimInsightUrl) {
		this.bimInsightUrl = bimInsightUrl;
	}

	public String getBimBenefitsUrl() {
		return bimBenefitsUrl;
	}

	public void setBimBenefitsUrl(String bimBenefitsUrl) {
		this.bimBenefitsUrl = bimBenefitsUrl;
	}

	public String getBimOneLinerDescription() {
		return bimOneLinerDescription;
	}

	public void setBimOneLinerDescription(String bimOneLinerDescription) {
		this.bimOneLinerDescription = bimOneLinerDescription;
	}

	public String getBimRating() {
		return bimRating;
	}

	public void setBimRating(String bimRating) {
		this.bimRating = bimRating;
	}

	public String getBimLayoutType() {
		return bimLayoutType;
	}

	public void setBimLayoutType(String bimLayoutType) {
		this.bimLayoutType = bimLayoutType;
	}

	public String getBimStatus() {
		return bimStatus;
	}

	public void setBimStatus(String bimStatus) {
		this.bimStatus = bimStatus;
	}

	public String getBimContentId() {
		return bimContentId;
	}

	public void setBimContentId(String bimContentId) {
		this.bimContentId = bimContentId;
	}
		

}
