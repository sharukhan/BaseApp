package com.neom.marketplace.common;

public class APIResponse {

	String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public APIResponse() {
		description = Constant.ON_CREATE_SUCCESS;
	}
	
	public APIResponse(Constant.Entities e) {
		description = String.format(Constant.ON_UPDATE_SUCCESS, e);
	}
}
